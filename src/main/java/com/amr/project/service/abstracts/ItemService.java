package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAll();

    void addItem(ItemDtoRequest itemDtoRequest);

    void updateItem(Long itemId, ItemDtoRequest itemDtoRequest);

    void deleteItem(Long itemId);

}
