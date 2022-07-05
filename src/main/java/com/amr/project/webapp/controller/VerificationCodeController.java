package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.repository.UserRepository;

import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class VerificationCodeController {

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "avito3_1";

    private final UserRepository userRepository;

    public VerificationCodeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generateQRUrl(String secret, String username) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, username, secret, APP_NAME), "UTF-8");
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("form_for_qrcode");
        return modelAndView;
    }

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws UnsupportedEncodingException {
        User userForQR = userRepository.findByUsername(user.getUsername());
        if (userForQR == null) {
            modelAndView.addObject("message", "Для получения QR кода, введите логин и пароль");
            modelAndView.setViewName("verify_error");
        } else {
            userForQR.setSecret(Base32.random());
            userRepository.save(userForQR);
            modelAndView.addObject("qr", generateQRUrl(userForQR.getSecret(), userForQR.getUsername()));
            modelAndView.setViewName("qrcode");
        }

        return modelAndView;
    }


}
