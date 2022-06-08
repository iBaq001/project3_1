package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.exception.ResourceNotFoundException;
import com.amr.project.mapper.ItemMapper;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    private final ItemMapper itemMapper;

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

}
