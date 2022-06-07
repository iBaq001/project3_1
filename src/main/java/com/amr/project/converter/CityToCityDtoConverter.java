package com.amr.project.converter;

import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.City;

public class CityToCityDtoConverter {

    public static CityDto convertCityToCityDto(City city) {
        return new CityDto(city.getId(),
                city.getName(),
                (city.getCountry() == null) ? null : city.getCountry().getName(),
                (city.getCountry() == null) ? null : city.getCountry().getId());
    }
}
