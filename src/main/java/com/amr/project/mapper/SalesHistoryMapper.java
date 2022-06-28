package com.amr.project.mapper;

import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.model.entity.report.SalesHistory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SalesHistoryMapper {


    SalesHistoryDto salesHistoryToSalesHistoryDto(SalesHistory salesHistory);
    SalesHistory salesHistoryDtoToSalesHistory(SalesHistoryDto salesHistoryDto);
}
