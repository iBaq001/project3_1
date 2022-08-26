package com.amr.project.model.dto;

import lombok.Data;



@Data
public class ItemDtoRequest {
    private String name;
    private Long basePrice;
    private Long price;
    private int count;
    private double rating;
    private String description;
}
