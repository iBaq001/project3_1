package com.amr.project.webapp.controller;

import com.amr.project.dao.abstracts.AddressDao;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
    private UserService userService;

    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }


    @GetMapping
    public String getAddresses(Model model, @ModelAttribute("address") String address) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("address", addressService.findAllAddresses());
        model.addAttribute("user", userService.findByUserName(user.getUsername()));
        return "admin_address";
    }

    @GetMapping("/newAddress")
    public String newItem(Model model) {
        model.addAttribute("address", new Address());
        return "newAddress";
    }



    @PostMapping("/{id}")
    public ModelAndView addAddress(@PathVariable("id") Long addressId, @ModelAttribute("address") AddressDto addressDto,
                                      ModelAndView modelAndView) {
        List<AddressDto> addressDtoList = addressService.findAllAddresses();
//        addressService.addAddress(addressDto);
        modelAndView.setViewName("redirect:/address");
        modelAndView.addObject("address", addressService.findById(addressId));
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView updateAddress(AddressDto addressDto, ModelAndView modelAndView,
                             @RequestParam("cityIndex") String cityIndex,
                             @RequestParam("country") String country,
                             @RequestParam("city") String city,
                             @RequestParam("street") String street,
                             @RequestParam("house") String house,
                             @PathVariable("id") Long id) {
        AddressDto addressDto1 = addressService.getAddressById(id);
        addressDto1.setCountry(country);
        addressDto1.setCity(city);
        addressDto1.setStreet(street);
        addressDto1.setHouse(house);
        addressDto1.setCityIndex(cityIndex);
        addressService.updateAddress(id, addressDto, city);
        modelAndView.setViewName("redirect:/address");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteItem(@PathVariable("id") Long id, ModelAndView modelAndView) {
        addressService.deleteAddress(id);
        modelAndView.setViewName("redirect:/items");
        return modelAndView;
    }

}
