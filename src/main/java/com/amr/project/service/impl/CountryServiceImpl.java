package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.mapper.CountryMapper;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CountryService;

import java.util.List;
import java.util.stream.Collectors;

public class CountryServiceImpl extends ReadWriteServiceImpl<Country, Long> implements CountryService {
    private final CountryDao countryDao;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryDao countryDao, CountryMapper countryMapper) {
        super(countryDao);
        this.countryDao = countryDao;
        this.countryMapper = countryMapper;
    }


    @Override
    public List<CountryDto> findAllCountryDto() {
        return countryDao.findAll().stream()
                .map(countryMapper::countryToCountryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CountryDto getCountryDtoById(Long id) {
        return countryMapper.countryToCountryDto(countryDao.findById(id));

    }

    @Override
    public void persist(CountryDto countryDto) {
        countryDao.persist(countryMapper.countryDtoToCountry(countryDto));
    }

    @Override
    public void updateCountry(Long id, CountryDto countryDto) {
        countryDao.update(countryMapper.countryDtoToCountry(countryDto));
    }

    @Override
    public void deleteCountry(Long id) {
        countryDao.deleteByIdCascadeIgnore(id);
    }
}
