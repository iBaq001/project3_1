package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.City;

import java.util.List;

public interface CityService extends ReadWriteService<City, Long>{
    List<CityDto> findAllCityDto();

    CityDto getCityDtoById(Long id);

    void persist(CityDto cityDto);

    CityDto findByCityName(String cityName);
    void updateCity(Long id, CityDto cityDto);

    void deleteCity(Long id);
}
