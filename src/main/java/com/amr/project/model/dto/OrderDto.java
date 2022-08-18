package com.amr.project.model.dto;

import com.amr.project.model.entity.Address;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class OrderDto {
    private Long id;
//    private AddressDto address;
//    private LocalDateTime date; - зачем оно в дто? предлагаю удалить
    private BigDecimal total;
    private Long userId;
    private List<ItemDto> itemsInOrder;
}
