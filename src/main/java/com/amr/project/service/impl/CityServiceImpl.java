package com.amr.project.service.impl;

import com.amr.project.converter.CityToCityDtoConverter;
import com.amr.project.dao.abstracts.CityDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.mapper.CityMapper;
import com.amr.project.model.dto.CityDto;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.CityService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class CityServiceImpl extends ReadWriteServiceImpl<City, Long> implements CityService {

    private final CityDao cityDao;
    private final CityMapper cityMapper;

    public CityServiceImpl(CityDao cityDao, CityMapper cityMapper) {
        super(cityDao);
        this.cityDao = cityDao;
        this.cityMapper = cityMapper;
    }

    @Override
    public List<CityDto> findAllCityDto() {
        return cityDao.findAll().stream()
                .map(cityMapper::cityToCityDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto getCityDtoById(Long id) {
        return cityMapper.cityToCityDto(cityDao.findById(id));
    }

    @Override
    public void persist(CityDto cityDto) {
        cityDao.persist(cityMapper.cityDtoToCity(cityDto));

    }

    @Override
    public CityDto findByCityName(String cityName) {
        return cityMapper.cityToCityDto(cityDao.findByCityName(cityName));
    }

    @Override
    public void updateCity(Long id, CityDto cityDto) {
        cityDao.update(cityMapper.cityDtoToCity(cityDto));
    }



    @Override
    public void deleteCity(Long id) {
        cityDao.deleteByIdCascadeIgnore(id);

    }
}
