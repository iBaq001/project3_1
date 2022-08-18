package com.amr.project.mapper;

import com.amr.project.converter.ImageToImageDtoConverter;
import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImageToImageDtoConverter.class, ItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "order.user.id", target = "userId")
    OrderDto orderToOrderDto (Order order);

    Order orderDtoToOrder (OrderDto orderDto);

    List<OrderDto> toListOrderDto (List<Order> orders);
}

