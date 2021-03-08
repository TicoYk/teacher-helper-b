package com.github.ticoyk.teacherhelperb.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    public static String SECRET;
    public static Long EXPIRATION_TIME;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String REGISTER_URL = "/api/users";
    public static final String LOGIN_URL = "/api/login";

    @Autowired
    public void loadSecurityConfig(
            @Value("${jwt.security.config.secret}") String secret,
            @Value("${jwt.security.config.expiration-time}") String expirationTime
        ){
        SECRET = secret;
        EXPIRATION_TIME = Long.parseLong(expirationTime);
    }
}
