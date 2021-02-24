package com.github.ticoyk.teacherhelperb.utils;

import java.io.Serializable;
import java.util.Date;

public interface AuthenticationUtil extends Serializable {

    String getUsername();
    Long getUserId();
    Boolean isAuthenticated();
    Boolean hasAuthorization();
    String generateToken();

}
