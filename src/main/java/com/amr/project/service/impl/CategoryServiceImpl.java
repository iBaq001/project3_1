package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.mapper.CategoryMapper;
import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category,Long> implements CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryDao categoryDao, CategoryMapper categoryMapper) {
        super(categoryDao);
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;


    }

    @Override
    public List<CategoryDto> findAllCategoriesByShopId(Long shopId) {
        return categoryDao.findAllCategoriesByShopId(shopId).stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }
}
