package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FeedbackService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/feedback_service")
public class FeedbackController {

    private UserService userService;
    private FeedbackService feedbackService;


    @Autowired
    public FeedbackController(UserService userService, FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @GetMapping
    public String show(@ModelAttribute("feedback") Feedback feedback) {
        return "feedback_service";
    }

    @PostMapping
    public String addFeedback(@ModelAttribute("feedback") Feedback feedback, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        feedback.setUsername(principal.getName());
        feedback.setUser(user);
        feedbackService.persist(feedback);
        return "redirect:/";
    }
}
