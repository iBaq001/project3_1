package com.amr.project.service.abstracts;


import com.amr.project.dao.abstracts.OrderDao;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Bill;
import com.amr.project.model.entity.Order;
import com.qiwi.billpayments.sdk.model.out.BillResponse;

public interface BillService{
    BillResponse connectionToQiwi (OrderDto orderDto);
    String getPayUrl (OrderDto orderDto);
}
