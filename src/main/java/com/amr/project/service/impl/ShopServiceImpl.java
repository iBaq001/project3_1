package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.mapper.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    private final ShopDao shopDao;
    private final ShopMapper shopMapper;

    public ShopServiceImpl(ShopDao shopDao, ShopMapper shopMapper) {
        super(shopDao);
        this.shopDao = shopDao;
        this.shopMapper = shopMapper;
    }

    @Override
    public List<ShopDto> getShopByName(String name) {
        return shopDao.getShopByName(name)
                .stream()
                .map(shopMapper::shopToShopDto)
                .collect(Collectors.toList());
    }
    @Override
    public ShopDto getShopDtoById(Long shopId) {
        return shopMapper.shopToShopDto(shopDao.findById(shopId));
    }

    @Transactional
    @Override
    public Shop getShopById(Long shopId) {
        return shopDao.findById(shopId);
    }

}
