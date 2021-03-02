package com.github.ticoyk.teacherhelperb.utils;

import static com.github.ticoyk.teacherhelperb.configurations.security.SecurityConstants.SECRET;
import static com.github.ticoyk.teacherhelperb.configurations.security.SecurityConstants.TOKEN_PREFIX;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.services.ApplicationUserService;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    ApplicationUserService applicationUserService;

    public JwtUtil(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    public ApplicationUser parseTokenToUser(String token) {
        String userEmail = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        return this.applicationUserService.findByEmail(userEmail.toString());
    }

}