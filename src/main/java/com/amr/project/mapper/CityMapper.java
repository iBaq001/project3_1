package com.amr.project.mapper;


import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface CityMapper {


    @Mapping(target = "countryName", source = "country.name")
    @Mapping(target = "countryId", source = "country.id")
    CityDto cityToCityDto(City city);

    @Mapping(target = "country.name", source = "countryName")
    @Mapping(target = "country.id", source = "countryId")
    City cityDtoToCity(CityDto city);
}
