package com.pizza.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SessionCleanFilter extends OncePerRequestFilter {
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
           throws ServletException, IOException {

      if (request.getRequestURI().equals("/Diylogin")) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         if (authentication != null && authentication.isAuthenticated()) {
            request.getSession().invalidate();
            SecurityContextHolder.clearContext();
         }
      }

      filterChain.doFilter(request, response);
   }
}
