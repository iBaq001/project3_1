package com.amr.project.service.impl;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.exception.ResourceNotFoundException;
import com.amr.project.mapper.ItemMapper;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private final ItemDao itemDao;
    private final ItemMapper itemMapper;
    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao, ItemMapper itemMapper, ItemRepo itemRepo) {
        super(itemDao);
        this.itemDao = itemDao;
        this.itemMapper = itemMapper;
        this.itemRepo = itemRepo;
    }


    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getAll() {
        return itemDao.findAll().stream()
                .map(itemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addItem(ItemDtoRequest itemDtoRequest) {
        itemDao.persist(itemMapper.createItemDtoToItem(itemDtoRequest));
    }

    @Transactional
    @Override
    public void updateItem(Long itemId, ItemDtoRequest itemDtoRequest) {
        Item updatingItem = itemDao.findById(itemId);
        if (Objects.nonNull(updatingItem)) {
            updatingItem.setBasePrice(itemDtoRequest.getBasePrice());
            updatingItem.setCount(itemDtoRequest.getCount());
            updatingItem.setName(itemDtoRequest.getName());
            updatingItem.setPrice(itemDtoRequest.getPrice());
            updatingItem.setRating(itemDtoRequest.getRating());
            updatingItem.setDescription(itemDtoRequest.getDescription());
            itemDao.persist(updatingItem);
        } else {
            throw new ResourceNotFoundException(String.format("Resource with id: %d", itemId));
        }
    }

    @Transactional
    @Override
    public void deleteItem(Long itemId) {
        Item deletingItem = itemDao.findById(itemId);
        if (Objects.nonNull(deletingItem)) {
            itemDao.delete(deletingItem);
        } else {
            throw new ResourceNotFoundException(String.format("Resource with id: %d", itemId));
        }
    }

    @Transactional
    @Override
    public Item getItemById(Long itemId) {
        return itemDao.findById(itemId);
    }

    @Transactional
    @Override
    public ItemDto getItemDtoById(Long itemId) {
        return itemMapper.itemToItemDto(getItemById(itemId));
    }

    @Override
    public void markForDeleteItem(Long itemId) {
        Item item = getItemById(itemId);
        item.setPretendedToBeDeleted(true);
        itemDao.update(item);
    }
    @Override
    public List<ItemDto> getItemByName(String name) {
        return itemDao.getItemsByName(name)
                .stream()
                .map(itemMapper::itemToItemDto)
                .collect(Collectors.toList());
    }

    public Page<ItemForShowcaseDto> findAll(Pageable pageable) {
        List<ItemForShowcaseDto> list = itemRepo.findAll(pageable)
                .getContent().stream().map(ItemToItemForShowcaseDtoConverter::convertItemToItemForShowcaseDto)
                .collect(Collectors.toList());
        Page<ItemForShowcaseDto> page = new PageImpl<>(list, pageable, list.size());

        return page;
    }

}
