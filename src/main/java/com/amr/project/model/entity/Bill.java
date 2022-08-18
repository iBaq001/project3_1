package com.amr.project.model.entity;

import com.amr.project.model.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity (name="bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String siteId;

    @Column
    private String billId;

    @Column
    private BigDecimal amount;

    @Column
    private String comment;

    @Column
    private BillStatus billStatus;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column
    private ZonedDateTime expirationDateTime;

//    @OneToOne(mappedBy = "bill")
//    private Order order;

    @Column
    private Long orderId;

    @Column
    private String payUrl;




}
