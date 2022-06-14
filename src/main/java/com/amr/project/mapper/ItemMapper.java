package com.amr.project.mapper;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.entity.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto itemToItemDto(Item item);

    Item createItemDtoToItem(ItemDtoRequest itemDtoRequest);

}
