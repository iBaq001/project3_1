package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.model.entity.report.SalesHistory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface SalesHistoryService extends ReadWriteService<SalesHistory, Long> {

    List<SalesHistoryDto> salesHistoryFilterByDates(List<SalesHistoryDto> salesHistoryDtoList, String typeOfFilter, Calendar date1, Calendar date2) throws ParseException;
    List<SalesHistoryDto> salesHistoryFilterByItems(List<SalesHistoryDto> salesHistoryDtoList, String[] itemDto);
    List<SalesHistoryDto> salesHistorySortByDate(List<SalesHistoryDto> salesHistoryDtoList, String typeOfDateSort);
    List<SalesHistoryDto> firstFilterBeforePersistAllSalesHistory(Long shopId);
    ShopDto getShopById(Long shopId);
    Set<CategoryDto> getCategoriesSet(Long shopId);

}
