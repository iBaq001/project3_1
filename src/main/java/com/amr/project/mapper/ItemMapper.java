package com.amr.project.mapper;

import com.amr.project.converter.ImageToImageDtoConverter;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto itemToItemDto(Item item);

    Item createItemDtoToItem(ItemDtoRequest itemDtoRequest);

    default ImageDto imageMap(Image image){
        return ImageToImageDtoConverter.convertImageToImageDto(image);
    }

}
