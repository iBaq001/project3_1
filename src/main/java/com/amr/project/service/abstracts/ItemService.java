package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService extends ReadWriteService<Item, Long> {

    List<ItemDto> getAll();

    void addItem(ItemDtoRequest itemDtoRequest);

    void updateItem(Long itemId, ItemDtoRequest itemDtoRequest);

    void deleteItem(Long itemId);

    Item getItemById(Long itemId);

    ItemDto getItemDtoById(Long itemId);

    void markForDeleteItem(Long itemId);

    List<ItemDto> getItemByName(String name);


    Page<ItemForShowcaseDto> findAll(Pageable pageable);

}
