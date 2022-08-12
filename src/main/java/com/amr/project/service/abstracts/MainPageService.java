package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Returns the some numbers(limit) of items or shops sorted by rating in desc order
 */
public interface MainPageService {

    /**
     * @param limit - needed numbers of item
     * @return Set<ItemForShowcaseDto> - set of ItemForShowcaseDto objects
     */
    List<ItemForShowcaseDto> getBestRatingItems(int limit);

    List<ShopDto> getBestRatingShops(int limit);
}
