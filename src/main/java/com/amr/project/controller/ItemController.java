package com.amr.project.controller;

import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.service.abstracts.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items/")
@RequiredArgsConstructor
public class ItemController {

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
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
