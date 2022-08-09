package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchStringController {

    final ShopService shopService;
    final ItemService itemService;

    @GetMapping(value = "/shopList/{name}")
    public ResponseEntity<?> getShopsByName(@PathVariable String name) {
        List<ShopDto> shopDto = shopService.getShopByName(name);
        return  shopDto != null && !shopDto.isEmpty()
                ? ResponseEntity.ok(shopDto)
                : ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/itemList/{name}")
    public ResponseEntity<?> getItemByName(@PathVariable String name) {
        List<ItemDto> itemDto = itemService.getItemByName(name);
        return itemDto != null && !itemDto.isEmpty()
                ? ResponseEntity.ok(itemDto)
                : ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }
}
