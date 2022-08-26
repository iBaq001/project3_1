package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.BillDao;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Bill;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.BillStatus;
import com.amr.project.service.abstracts.BillService;
import com.amr.project.service.abstracts.UserService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.in.Customer;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {



    private final String SECRET_KEY = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6Ijc1dnA3OC0wMCIsInVzZXJfaWQiOiI3OTIxODQ2MDA4NiIsInNlY3JldCI6ImRhNDNmMThkOWUwMTAzOGU5MWZhNjBjZGZkNzdhY2E3MTM0MTcwNDVlNTU0ZjlhZmMyNGQ5MWQ2MmUzMjNkMDgifX0=";

    @Autowired
    private UserService userService;

    public BillPaymentClient getClient() {return BillPaymentClientFactory.createDefault(SECRET_KEY);}

    @SneakyThrows
    @Transactional
    @Override
    public BillResponse connectionToQiwi(OrderDto orderDto) {
        User customer = userService.findById(orderDto.getUserId());
        BillPaymentClient client = getClient();


        CreateBillInfo billInfo = new CreateBillInfo(
                UUID.randomUUID().toString(),
                new MoneyAmount(
                        orderDto.getTotal(),
                        Currency.getInstance("RUB")
                ), "Your comment here",
                ZonedDateTime.now().plusDays(45),
                new Customer(customer.getEmail(),
                        customer.getId().toString(),
                        customer.getPhone()
                ),
                "http://localhost:8080"
        );

        //Сохранение счета в базу данных, не нужно
//        Bill bill = Bill.builder()
//                .billId(billInfo.getBillId())
//                .amount(billInfo.getAmount().getValue())
//                .orderId(orderDto.getId())
//                .billStatus(BillStatus.WAITING)
//                .expirationDateTime(billInfo.getExpirationDateTime())
//                .build();
//        billDao.persist(bill);

        return client.createBill(billInfo);
    }

    public String getPayUrl (OrderDto orderDto) {
        return connectionToQiwi(orderDto).getPayUrl();
    }
}
