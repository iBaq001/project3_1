package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainPageServiceImplTest {

    private static final Integer LIMIT = 12;

    @Mock
    private ShopDao shopDao;

    @Mock
    private ItemDao itemDao;

    @InjectMocks
    private MainPageServiceImpl mainPageService;

    static Item testItem;
    private static Shop testShop;

    @BeforeAll
    static void prepareData() {

        testShop = Shop.builder()
                .id(1L)
                .count(1)
                .description("BeerЛога - лучший магазин пенной продукции")
                .email("BeerLoga@mail.ru")
                .name("BeerЛога")
                .rating(9.9)
                .logo(new Image(3L, new byte[]{1, 2, 3}, "image/jpeg", true))
                .build();

        testItem = Item.builder()
                .id(1L)
                .name("Хадыженское")
                .rating(9.5)
                .shop(testShop)
                .images(List.of(new Image(2L, new byte[]{1, 2, 3}, "image/jpeg", true))) // without images we'll
                // catch NPE
                .build();
    }

    @Test
    void getBestRatingItems() {
        doReturn(List.of(testItem))
                .when(itemDao).getBestRatingItems(LIMIT);

        var actualResult = mainPageService.getBestRatingItems(LIMIT);
        assertFalse(actualResult.isEmpty());

        assertTrue(actualResult.stream().anyMatch(el -> el.getId() == 1L));
        assertTrue(actualResult.stream().anyMatch(el -> el.getName().equals("Хадыженское")));
        assertTrue(actualResult.stream().anyMatch(el -> el.getRating() == 9.5));

        verify(itemDao).getBestRatingItems(12);
        verifyNoMoreInteractions(itemDao);
    }

    @Test
    void getBestRatingShops() {
        doReturn(List.of(testShop))
                .when(shopDao).getBestRatingShops(LIMIT);

        var actualResult = mainPageService.getBestRatingShops(LIMIT);
        assertFalse(actualResult.isEmpty());

        assertTrue(actualResult.stream().anyMatch(el -> el.getId() == 1L));
        assertTrue(actualResult.stream().anyMatch(el -> el.getName().equals("BeerЛога")));
        assertTrue(actualResult.stream().anyMatch(el -> el.getEmail().equals("BeerLoga@mail.ru")));
        assertTrue(actualResult.stream().anyMatch(el -> el.getRating() == 9.9));

        verify(shopDao).getBestRatingShops(12);
        verifyNoMoreInteractions(shopDao);
    }
}