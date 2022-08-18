package com.amr.project.webapp.controller;

import com.amr.project.dao.abstracts.UserDao;

import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;

import com.amr.project.service.impl.MailSenderRealizationImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class UserRegistrationController {


    private final UserDao userDao;
    private final MailSenderRealizationImpl emailSenderService;

    @Autowired
    public UserRegistrationController(UserDao userDao, MailSenderRealizationImpl emailSenderService) {

        this.userDao = userDao;
        this.emailSenderService = emailSenderService;
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup_form");
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView registerUser(ModelAndView modelAndView, User user) {

        User existingUser = userDao.findUserByEmail(user.getEmail());
        if (existingUser != null) {
            modelAndView.addObject("message", "Пользователь с таким e-mail уже существует");
            modelAndView.setViewName("verify_error");
        } else {
            user.setActivationCode(UUID.randomUUID().toString());
            userDao.persist(user);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setText("Чтобы продолжить регистрацию, перейдите по ссылке: "
                    + "http://localhost:8080/confirm-account?token="
                    + user.getActivationCode());
            emailSenderService.send(user.getEmail(), "E-mail verification", String.valueOf(mailMessage));
            modelAndView.addObject("emailId", user.getEmail());
            modelAndView.setViewName("verify_sent");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken, User user) {
        User currentUser = userDao.findByActivationCode(confirmationToken);
        if (currentUser.getActivationCode() != null) {
            currentUser.setActivate(true);
            currentUser.setActivationCode(confirmationToken);
            currentUser.setRole(Roles.USER);
            userDao.persist(currentUser);
            modelAndView.setViewName("verify_success");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("verify_error");
        }
        return modelAndView;

    }


}
