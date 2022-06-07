package com.amr.project.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDtoRequest {
    private String name;
    private BigDecimal basePrice;
    private BigDecimal price;
    private int count;
    private double rating;
    private String description;
}
