package com.amr.project.mapper;

import com.amr.project.model.dto.BillDto;
import com.amr.project.model.entity.Bill;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BillMapper {

    BillDto billToBillDto (Bill bill);

    Bill billDtoToBill (BillDto billDto);
}
