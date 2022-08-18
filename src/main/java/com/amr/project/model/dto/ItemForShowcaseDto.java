package com.amr.project.model.dto;

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
public class ItemForShowcaseDto {
    private Long id;
    private String name;
    private String description;
    private Long basePrice;
    private Long price;
    private int count;
    private ImageDto imageDto;
    private ShopDto shopDto;
    private double rating;
    private List ratingFilledStars;
    private List ratingEmptyStars;
}
