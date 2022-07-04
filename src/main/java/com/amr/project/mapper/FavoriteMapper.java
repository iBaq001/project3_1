package com.amr.project.mapper;

import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ItemMapper.class, ShopMapper.class})
public interface FavoriteMapper {

    FavoriteDto favoriteToFavoriteDto(Favorite favorite);

}
