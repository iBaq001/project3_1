package com.amr.project.mapper;


import com.amr.project.converter.ImageToImageDtoConverter;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CouponMapper.class})
public abstract class UserMapper {

    @Autowired
    protected AddressMapper addressMapper;

    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }



    abstract public List<UserDto> toUserDtos(List<User> users);


    abstract public User userDtoToUser(UserDto userDto);



    public abstract UserDto userToUserDto(User user);



    // вспомогательные методы
    List<AddressDto> addressMap(Address value) {
        List<AddressDto> result = new ArrayList<>();
        AddressDto add = addressMapper.addressToAddressDto(value);
        result.add(add);
        return result;
    }

    ImageDto imageMap(List<Image> images) {
        if (images.isEmpty()) {
            return ImageToImageDtoConverter.convertImageToImageDto(null);
        } else {
            Optional<Image> optional = images.stream().filter(image -> image.getIsMain()).findFirst();
            return ImageToImageDtoConverter.convertImageToImageDto(optional.orElse(null));
        }
    }

}
