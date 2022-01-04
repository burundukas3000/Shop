package com.project.backend.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("ATTEMPT AUTHENTICATION Filter");
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {

            UsernamePasswordAuthenticationToken authRequest = this.getAuthRequest(request);
            super.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String email = request.getParameter("email");
        String userNameEmail = String.format("%s%s%s", username.trim(), String.valueOf(Character.LINE_SEPARATOR), email);
        System.out.println("Filter. Setting token for mixed username+email and password "+userNameEmail);
        return new UsernamePasswordAuthenticationToken(userNameEmail, password);
    }

}