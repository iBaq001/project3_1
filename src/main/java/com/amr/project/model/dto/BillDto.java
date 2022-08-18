package com.amr.project.model.dto;

import com.amr.project.model.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id", scope = Long.class)
public class BillDto {
    private Long id;
    private String siteId;
    private String billId;
    private BigDecimal amount;
    private String comment;
    private BillStatus status;
    private Long orderId;
    private String payUrl;
}
