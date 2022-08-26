package com.amr.project.service.abstracts;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;

import java.util.List;

public interface AddressService extends ReadWriteService<Address, Long> {
    List<AddressDto> findAllAddresses();

    AddressDto getAddressById(Long id);

    void addAddress(AddressDto addressDto, String cityName);


    void updateAddress(Long id, AddressDto addressDto, String cityName);

    void deleteAddress(Long id);
}
