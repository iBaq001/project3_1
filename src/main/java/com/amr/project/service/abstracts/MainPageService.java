package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;

import java.util.List;
import java.util.Set;

public interface MainPageService {
    Set<ItemForShowcaseDto> getBestRatingItems();

    Set<ShopDto> getBestRatingShops();
}
