package testproject;

import com.amr.project.mapper.SalesHistoryMapper;
import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.report.SalesHistory;
import com.amr.project.service.impl.SalesHistoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class SalesHistoryServiceUnitTest {

    private SalesHistoryMapper salesHistoryMapper = Mappers.getMapper(SalesHistoryMapper.class);

    @InjectMocks
    private SalesHistoryServiceImpl salesHistoryService;

    @Test
    public void salesHistorySortByDateTest() {
        Calendar calendar1 = new Calendar.Builder().setDate(2022, 6, 1).build();
        Calendar calendar2 = new Calendar.Builder().setDate(2022, 4, 1).build();
        Calendar calendar3 = new Calendar.Builder().setDate(2022, 5, 1).build();
        SalesHistory salesHistory1 = SalesHistory.builder()
                .id(1L)
                .basePrice(BigDecimal.valueOf(100L))
                .price(BigDecimal.valueOf(300L))
                .count(20)
                .orderDate(calendar1)
                .item(new Item())
                .build();

        SalesHistory salesHistory2 = SalesHistory.builder()
                .id(2L)
                .basePrice(BigDecimal.valueOf(50L))
                .price(BigDecimal.valueOf(100L))
                .count(10)
                .orderDate(calendar2)
                .item(new Item())
                .build();

        SalesHistory salesHistory3 = SalesHistory.builder()
                .id(3L)
                .basePrice(BigDecimal.valueOf(150L))
                .price(BigDecimal.valueOf(200L))
                .count(15)
                .orderDate(calendar3)
                .item(new Item())
                .build();

        String FilterWord1 = null;
        String FilterWord2 ="New";
        String FilterWord3 ="Old";


        List<SalesHistoryDto> salesHistoryDtoList = new ArrayList<>();
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory1));
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory2));
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory3));

        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord1).get(0).getId().equals(salesHistory1.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord1).get(1).getId().equals(salesHistory2.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord1).get(2).getId().equals(salesHistory3.getId()));

        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord2).get(0).getId().equals(salesHistory1.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord2).get(1).getId().equals(salesHistory3.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord2).get(2).getId().equals(salesHistory2.getId()));

        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord3).get(0).getId().equals(salesHistory2.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord3).get(1).getId().equals(salesHistory3.getId()));
        Assert.assertTrue(salesHistoryService.salesHistorySortByDate(salesHistoryDtoList,FilterWord3).get(2).getId().equals(salesHistory1.getId()));

    }

    @Test
    public void salesHistoryFilterByItemsTest() {
        Calendar calendar1 = new Calendar.Builder().setDate(2022, 6, 1).build();
        Calendar calendar2 = new Calendar.Builder().setDate(2022, 4, 1).build();
        Calendar calendar3 = new Calendar.Builder().setDate(2022, 5, 1).build();
        SalesHistory salesHistory1 = SalesHistory.builder()
                .id(1L)
                .basePrice(BigDecimal.valueOf(100L))
                .price(BigDecimal.valueOf(300L))
                .count(20)
                .orderDate(calendar1)
                .item(Item.builder().name("item1").build())
                .build();

        SalesHistory salesHistory2 = SalesHistory.builder()
                .id(2L)
                .basePrice(BigDecimal.valueOf(50L))
                .price(BigDecimal.valueOf(100L))
                .count(10)
                .orderDate(calendar2)
                .item(Item.builder().name("item2").build())
                .build();

        SalesHistory salesHistory3 = SalesHistory.builder()
                .id(3L)
                .basePrice(BigDecimal.valueOf(150L))
                .price(BigDecimal.valueOf(200L))
                .count(15)
                .orderDate(calendar3)
                .item(Item.builder().name("item3").build())
                .build();

        String[] arrayOfItemsName1 = null;
        String[] arrayOfItemsName2 = {"item1"};
        String[] arrayOfItemsName3 = {"item1", "item2", "item3"};


        List<SalesHistoryDto> salesHistoryDtoList = new ArrayList<>();
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory1));
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory2));
        salesHistoryDtoList.add(salesHistoryMapper.salesHistoryToSalesHistoryDto(salesHistory3));

        Assert.assertEquals(salesHistoryService.salesHistoryFilterByItems(salesHistoryDtoList, arrayOfItemsName1), new ArrayList<>());

        Assert.assertEquals(salesHistoryService.salesHistoryFilterByItems(salesHistoryDtoList, arrayOfItemsName2).size(), 1);
        Assert.assertTrue(salesHistoryService.salesHistoryFilterByItems(salesHistoryDtoList, arrayOfItemsName2).get(0).getItem().getName().equals(salesHistory1.getItem().getName()));

        Assert.assertEquals(salesHistoryService.salesHistoryFilterByItems(salesHistoryDtoList, arrayOfItemsName3), salesHistoryDtoList);

    }

}
