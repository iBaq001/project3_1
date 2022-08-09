package com.amr.project.integration.service;

import com.amr.project.integration.annotation.IT;
import com.amr.project.service.abstracts.MainPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
public class MainPageServiceTest {

    private final static int LIMIT = 12;

    @Autowired
    private MainPageService mainPageService;

    @Test
    void getBestRatingItems() {
        var actualResult = mainPageService.getBestRatingItems(LIMIT);
        assertFalse(actualResult.isEmpty());
        assertTrue(actualResult.get(0).getRating() >= actualResult.get(1).getRating());
    }

    @Test
    void getBestRatingShops() {
        var actualResult = mainPageService.getBestRatingShops(LIMIT);
        assertFalse(actualResult.isEmpty());
        assertTrue(actualResult.get(0).getRating() >= actualResult.get(1).getRating());
    }
}
