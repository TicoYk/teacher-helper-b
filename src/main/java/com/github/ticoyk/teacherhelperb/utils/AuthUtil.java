package com.github.ticoyk.teacherhelperb.utils;

import com.github.ticoyk.teacherhelperb.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;

public interface AuthUtil extends Serializable {

    String getUsername(String token);
    Long getUserId(String token);
    Date getExpirationDate(String token);

    Boolean isAuthenticated(String token, UserDetails userDetails);
    Boolean hasAuthorization();

    String generateToken(UserDetails userDetails);

}
