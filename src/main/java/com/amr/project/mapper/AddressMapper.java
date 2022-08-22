package com.amr.project.mapper;


import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, CityMapper.class})
public interface AddressMapper {

@Mapping(target = "city", source = "city.name")
@Mapping(target = "cityId", source = "city.id")
@Mapping(target = "country", source = "city.country.name")
@Mapping(target = "countryId", source = "city.country.id")
    AddressDto addressToAddressDto(Address address);

    @Mapping(target = "city.name", source = "city")
    @Mapping(target = "city.id", source = "cityId")
    @Mapping(target = "city.country.name", source = "country")
    @Mapping(target = "city.country.id", source = "countryId")
    Address addressDtoToAddress (AddressDto addressDto);


}
