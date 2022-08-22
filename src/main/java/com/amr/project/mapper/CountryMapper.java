package com.amr.project.mapper;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.entity.Country;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryMapper {
    CountryDto countryToCountryDto(Country country);
    Country countryDtoToCountry(CountryDto countryDto);
}
