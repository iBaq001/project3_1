package com.amr.project.webapp.controller;

import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.service.abstracts.SalesHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/shopSalesReport/")
@RequiredArgsConstructor
public class SalesReportRestController {

    private final SalesHistoryService salesHistoryService;

    @GetMapping("{shopId}")
    public ModelAndView setItemsForFilters(@PathVariable Long shopId,
                                           @RequestParam(value = "typeOfFilter", required = false) String typeOfFilter,
                                           @RequestParam(value = "itemsDto", required = false) String[] itemsDto,
                                           @RequestParam(value = "date1", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar date1,
                                           @RequestParam(value = "date2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Calendar date2,
                                           @RequestParam(value = "typeOfDateSort", required = false) String typeOfDateSort) throws ParseException {

        ModelAndView model = new ModelAndView("shopSalesReport");

        List<SalesHistoryDto> filteredListOfSalesHistory =
                salesHistoryService.salesHistorySortByDate(
                        salesHistoryService.salesHistoryFilterByDates(
                                salesHistoryService.salesHistoryFilterByItems(
                                        (salesHistoryService.getAllShopSalesHistoryById(shopId))
                                        , itemsDto), typeOfFilter, date1, date2), typeOfDateSort);

        model.addObject("categories", salesHistoryService.getCategoriesList(shopId));
        model.addObject("salesHistory", filteredListOfSalesHistory);
        model.addObject("shop", salesHistoryService.getShopById(shopId));
        model.addObject("simpleDateFormat", new SimpleDateFormat("dd/MM/yyyy"));

        return model;
    }
}
