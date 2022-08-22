package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

//@RestController
@Controller
public class UserController {
    private UserDetailsServiceImpl userDetailsService;
    private UserService userService;

    private ItemService itemService;


    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService, UserService userService, ItemService itemService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.getAll());
        model.addAttribute("user", userDetailsService.findUserByUsername(user.getUsername()));
        return "/admin";
    }
    @DeleteMapping("/admin/deleteUser")
    public ModelAndView deleteUser(@RequestParam Long userId, ModelAndView modelAndView) {
        userService.deleteById(userId);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping("admin/create/")
    public ModelAndView createUser(@ModelAttribute("user") User user, @RequestParam("roles") String role, ModelAndView modelAndView) {
        List<User> userDtoList = userService.findAll();
        Roles roleAdd = Roles.valueOf(role);
        user.setRole(roleAdd);
        userService.persist(user);
        modelAndView.setViewName("redirect:/admin");
        modelAndView.addObject("users", userDtoList.add(user));
        modelAndView.addObject("user", userDtoList);
        return modelAndView;
    }

    @PatchMapping("admin/edit/{id}")
    public ModelAndView update(@PathVariable Long id,
                               @RequestParam("role") String role,
                               ModelAndView modelAndView,
                               @ModelAttribute("user") User user,
                               @RequestParam("password") String password,
                               @RequestParam("phone") String phone,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        User user2 =  userService.findById(id);
        user2.setPhone(phone);
        user2.setLastName(lastName);
        user2.setFirstName(firstName);
        user2.setPassword(password);
        user2.setRole(Roles.valueOf(role));
        userService.update(user2);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    //контроллер для инфо о текущем юзере (можно доработать в личный кабинет)
    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userDetailsService.findUserByUsername(user.getUsername()));
        return "/user";
    }
}
