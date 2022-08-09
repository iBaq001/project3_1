package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.ChatNotification;
import com.amr.project.model.entity.Message;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ChatService;
import com.amr.project.service.abstracts.MessageService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;
    private final UserService userService;

    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService, ChatService chatService, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.chatService = chatService;
        this.userService = userService;
    }

    @MessageMapping("/chat")
    @SendToUser("/user/private-message")
    public void processMessage(@Payload Message message) {
        Chat chat = chatService.findBySenderAndRecipient(message.getSender(), message.getRecipient(), true).get();
        message.setChat(chat);

        Message savedMessage = messageService.save(message);

        messagingTemplate.convertAndSendToUser(
                message.getRecipient().getId().toString(),
                "/queue/messages",
                new ChatNotification(
                        savedMessage.getId(),
                        savedMessage.getSender().getId(),
                        savedMessage.getSender().getUsername())
        );
    }

    @GetMapping("/private-message/{id}")
    public String chatPage(Model model,
                           @AuthenticationPrincipal User authUser,
                           @PathVariable Long id) {
        User otherUser = userService.findById(id);
        model.addAttribute("authUser", authUser);
        model.addAttribute("otherUser", otherUser);
        return "chat";
    }
}
