package com.pizza.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DIYAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = "/";

        if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("Manager")) {
            targetUrl = "/managerMenu";
        } else if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("Customer")) {
            targetUrl = "/menu";
        }

        response.sendRedirect(targetUrl);
    }

}
