package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;

import java.util.List;

public interface CountryService extends ReadWriteService<Country, Long>{
    List<CountryDto> findAllCountryDto();

    CountryDto getCountryDtoById(Long id);

    void persist(CountryDto countryDto);

    void updateCountry(Long id, CountryDto countryDto);

    void deleteCountry(Long id);
}
