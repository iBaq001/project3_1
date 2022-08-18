package com.amr.project.model.dto;

import com.amr.project.model.entity.Item;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class CartItemDto {
    private Long id;
    private Long itemId;
    private Long shopId;

//    id добавлен для связи юзера с корзиной
    private Long userId;

    private int quantity;
    private Long price;
    private String name;
    private List<ImageDto> images;
}
