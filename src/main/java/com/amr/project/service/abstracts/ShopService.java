package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopService extends ReadWriteService<Shop, Long> {
    List<ShopDto> getShopByName(String name);
    ShopDto getShopDtoById(Long shopId);
}
