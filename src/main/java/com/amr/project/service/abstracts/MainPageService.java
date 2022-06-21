package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;

import java.util.Set;

public interface MainPageService {
    Set<ItemForShowcaseDto> getBestRatingItems(int limit);

    Set<ShopDto> getBestRatingShops(int limit);
}
