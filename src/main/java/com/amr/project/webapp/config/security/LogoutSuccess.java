package com.amr.project.webapp.config.security;

import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LogoutSuccess implements LogoutSuccessHandler {
    private final CartItemService cartItemService;
    private final UserService userService;

    @Autowired
    public LogoutSuccess(CartItemService cartItemService, UserService userService) {
        this.cartItemService = cartItemService;
        this.userService = userService;

    }
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        cartItemService.setUser(null);
        httpServletResponse.sendRedirect("/");
    }
}
