package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.mapper.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopService {

    private final ShopDao shopDao;
    private final ShopMapper shopMapper;

    private final ShopRepo shopRepo;

    public ShopServiceImpl(ShopDao shopDao, ShopMapper shopMapper, ShopRepo shopRepo) {
        super(shopDao);
        this.shopDao = shopDao;
        this.shopMapper = shopMapper;
        this.shopRepo = shopRepo;
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

    @Override
    public Page<ShopDto> findAll(Pageable pageable) {
        List<ShopDto> list = shopRepo.findAll(pageable)
                .getContent().stream().map(shopMapper::shopToShopDto)
                .collect(Collectors.toList());
        Page<ShopDto> page = new PageImpl<>(list, pageable, list.size());

        return page;
    }

}
