package com.amr.project.service.impl;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.exception.ResourceNotFoundException;
import com.amr.project.mapper.ShopMapper;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShowcaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShowcaseServiceImpl implements ShowcaseService {
    ItemDao itemDao;
    ShopDao shopDao;
    ShopMapper shopMapper;


    public ShowcaseServiceImpl() {
    }

    @Override
    @Transactional
    public Shop findById(Long id) {
        return shopDao.findById(id);
    }

    @Override
    @Transactional
    public void removeShop(Shop shop) {
        shopDao.delete(shop);
    }

    @Override
    public List<Category> returnCategoryOfItemsInTheShop(Long shopId) {
        return findById(shopId).getItems().stream()
                .map(item -> item.getCategory())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemForShowcaseDto> itemsDtoOfTheShop(Long shopId) {
        return itemDao.getItemsByShopId(shopId)
                .stream().map(item -> ItemToItemForShowcaseDtoConverter.convertItemToItemForShowcaseDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDto> getAllShopDto() {
        return shopDao.findAll().stream()
                .map(shop -> ShopToShopDtoConverter.convertShopToShopDto(shop))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto getShopDtoById(Long id) {
        return ShopToShopDtoConverter.convertShopToShopDto(shopDao.findById(id));
    }

    @Override
    public void addShop(ShopDto shopDto) {
        shopDao.persist(shopMapper.createShopDtoToShop(shopDto));
    }

    @Override
    public ShopDto getShopDtoByName(String name) {
        return ShopToShopDtoConverter.convertShopToShopDto(shopDao.findShopByName(name));
    }

    @Override
    public void removeShopById(Long id) {
        Shop deletingShop = shopDao.findById(id);
        if(Objects.nonNull(deletingShop)) {
            shopDao.delete(findById(id));
        } else {
            throw new ResourceNotFoundException(String.format("Resource with id: %d", id));
        }
    }

    @Override
    public void updateShop(Long id, ShopDto shopDto) {
        Shop updatingShop = shopDao.findById(id);

        if (Objects.nonNull(updatingShop)) {
            updatingShop.setName(shopDto.getName());
            updatingShop.setDescription(shopDto.getDescription());
            updatingShop.setEmail(shopDto.getEmail());
            updatingShop.setPhone(shopDto.getPhone());
            shopDao.persist(updatingShop);
        } else {
            throw new ResourceNotFoundException(String.format("Resource with id: %d", id));
        }
    }
}
