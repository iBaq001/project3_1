package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.OrderDao;
import com.amr.project.dao.abstracts.SalesHistoryDao;
import com.amr.project.mapper.CategoryMapper;
import com.amr.project.mapper.SalesHistoryMapper;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.report.SalesHistory;
import com.amr.project.service.abstracts.SalesHistoryService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesHistoryServiceImpl extends ReadWriteServiceImpl<SalesHistory, Long> implements SalesHistoryService {

    private final SalesHistoryDao salesHistoryDao;
    private final OrderDao orderDao;
    private final ShopService shopService;
    private final CategoryMapper categoryMapper;
    private final SalesHistoryMapper salesHistoryMapper;


    public SalesHistoryServiceImpl(SalesHistoryDao salesHistoryDao,
                                   OrderDao orderDao,
                                   ShopService shopService,
                                   CategoryMapper categoryMapper,
                                   SalesHistoryMapper salesHistoryMapper) {
        super(salesHistoryDao);
        this.salesHistoryDao = salesHistoryDao;
        this.orderDao = orderDao;
        this.shopService = shopService;
        this.categoryMapper = categoryMapper;
        this.salesHistoryMapper = salesHistoryMapper;
    }


    @Override
    public List<SalesHistoryDto> getAllShopSalesHistoryById(Long shopId) {

        List<SalesHistoryDto> salesHistories = new ArrayList<>();
        List<Order> ordersList = orderDao.findAll();
        List<Item> itemsListByShopId = orderDao.itemsListByShopId(shopId);
        if (ordersList == null) {
            Objects.requireNonNull(ordersList, "ordersList не может быть null");
        }
        if (itemsListByShopId == null) {
            Objects.requireNonNull(itemsListByShopId, "itemsListByShopId не может быть null");
        }
        if (!ordersList.isEmpty() && !itemsListByShopId.isEmpty()) {
            for (Order order : ordersList) {
                for (Item item : order.getItemsInOrder()) {
                    if (itemsListByShopId.contains(item)) {
                        int count = 0;
                        for (Item itemForCount : order.getItemsInOrder()) {
                            if (item.equals(itemForCount)) {
                                count++;
                            }
                        }
                        SalesHistory salesHistory = SalesHistory.builder()
                                .orderDate(order.getOrderDate())
                                .price(item.getPrice())
                                .basePrice(item.getBasePrice())
                                .item(item)
                                .count(count)
                                .build();
                        if(!salesHistories.contains(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory))) {
                            salesHistories.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory));
                        }
                    }
                }
            }
        }

        return salesHistories;
    }


    @Override
    public List<SalesHistoryDto> salesHistoryFilterByItems(List<SalesHistoryDto> salesHistoryDtoList, String[] items) {
        List<SalesHistoryDto> filteredSalesHistoryDtoList = new ArrayList<>();
        if (items != null) {
            for (String nameOfItemDto : items) {
                List<SalesHistoryDto> filteredList = salesHistoryDtoList.stream()
                        .filter(salesHistoryDto -> salesHistoryDto.getItem().getName().equals(nameOfItemDto))
                        .collect(Collectors.toList());
                filteredSalesHistoryDtoList.addAll(filteredList);
            }
        }
        return filteredSalesHistoryDtoList;
    }


    @Override
    public List<SalesHistoryDto> salesHistoryFilterByDates(List<SalesHistoryDto> salesHistoryDtoList,
                                                           String typeOfFilter,
                                                           Calendar date1,
                                                           Calendar date2) {

        List<SalesHistoryDto> allSalesFiltered = new ArrayList<>();
        if (typeOfFilter != null) {
            switch (typeOfFilter) {
                case "AllTheTime":
                    return salesHistoryDtoList;
                case "FromDateToDate":
                    allSalesFiltered = salesHistoryDtoList.stream()
                            .filter(salesHistoryDto -> (salesHistoryDto.getOrderDate().getTimeInMillis() >= date1.getTimeInMillis()
                                    && salesHistoryDto.getOrderDate().getTimeInMillis() <= date2.getTimeInMillis()))
                            .collect(Collectors.toList());
                    return allSalesFiltered;
                default:
                    return salesHistoryDtoList.stream()
                            .filter(salesHistoryDto ->
                                    (salesHistoryDto.getOrderDate().getTimeInMillis() >= dateForFilter(Calendar.getInstance(), typeOfFilter).getTimeInMillis()))
                            .collect(Collectors.toList());
            }
        }
        return allSalesFiltered;
    }

    @Override
    public List<SalesHistoryDto> salesHistorySortByDate(List<SalesHistoryDto> salesHistoryDtoList, String typeOfDateSort) {

        if (typeOfDateSort != null && typeOfDateSort.equals("New")) {
            salesHistoryDtoList.sort((x, y) -> y.getOrderDate().getTime().compareTo(x.getOrderDate().getTime()));
            return salesHistoryDtoList;

        } else if (typeOfDateSort != null && typeOfDateSort.equals("Old")) {
            salesHistoryDtoList.sort(Comparator.comparing(x -> x.getOrderDate().getTime()));
            return salesHistoryDtoList;

        }
        return salesHistoryDtoList;
    }

    @Override
    public ShopDto getShopById(Long shopId) {
        return shopService.getShopDtoById(shopId);
    }

    @Override
    public List<CategoryDto> getCategoriesList(Long shopId) {
        List<CategoryDto> categoryDtoList = salesHistoryDao.findAllCategoriesByShopId(shopId).stream()
                .map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());
        if (categoryDtoList == null) {
            Objects.requireNonNull(categoryDtoList, "categoryDtoSet не может быть null");
        } else if (!categoryDtoList.isEmpty()) {
            return categoryDtoList;
        }
        return categoryDtoList;
    }

    private Calendar dateForFilter(Calendar date, String typeOfFilter) {
        switch (typeOfFilter) {
            case "Week":
                date.add(Calendar.WEEK_OF_MONTH, -1);
                break;
            case "Month":
                date.add(Calendar.MONTH, -1);
                break;
            case "HalfYear":
                date.add(Calendar.MONTH, -6);
                break;
            case "Year":
                date.add(Calendar.YEAR, -1);
                break;
        }
        return date;
    }
}
