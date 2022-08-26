package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.mapper.AddressMapper;
import com.amr.project.mapper.CityMapper;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.City;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl extends ReadWriteServiceImpl<Address, Long> implements AddressService {

    private AddressDao addressDao;
    private AddressMapper addressMapper;
    private CityMapper cityMapper;
    private CityService cityService;

    public AddressServiceImpl(AddressDao addressDao, AddressMapper addressMapper, CityMapper cityMapper, CityService cityService) {
        super(addressDao);
        this.addressDao = addressDao;
        this.addressMapper = addressMapper;
        this.cityMapper = cityMapper;
        this.cityService = cityService;
    }


    @Override
    public List<AddressDto> findAllAddresses() {
        return addressDao.findAll().stream()
                .map(addressMapper::addressToAddressDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return addressMapper.addressToAddressDto(addressDao.findById(id));
    }

    @Override
    public void addAddress(AddressDto addressDto, String cityName) {
        addressDao.persist(addressMapper.addressDtoToAddress(addressDto));
    }

    @Override
    public void updateAddress(Long id, AddressDto addressDto, String cityName) {
        Address address = addressMapper.addressDtoToAddress(addressDto);
        City city = cityMapper.cityDtoToCity(cityService.findByCityName(cityName));
        address.setCity(city);
        addressDao.update(address);
    }


    @Override
    public void deleteAddress(Long id) {
        addressDao.deleteByIdCascadeIgnore(id);
    }
}
