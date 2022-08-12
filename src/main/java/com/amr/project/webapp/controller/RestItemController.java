package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items/")
@RequiredArgsConstructor
public class RestItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody ItemDtoRequest itemDtoRequest) {
        itemService.addItem(itemDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable Long itemId,
                                           @RequestBody ItemDtoRequest itemDtoRequest) {
       itemService.updateItem(itemId, itemDtoRequest);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Roles.ADMIN || user.getRole() == Roles.MODERATOR) {
            itemService.deleteItem(itemId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else if (user.getRole() == Roles.USER) {
            itemService.markForDeleteItem(itemId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("{itemId}")
    @ResponseBody
    public ItemDto getItemById(@PathVariable Long itemId) {
        return itemService.getItemDtoById(itemId);
    }

}
