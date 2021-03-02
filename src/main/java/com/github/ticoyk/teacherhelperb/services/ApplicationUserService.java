package com.github.ticoyk.teacherhelperb.services;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;

public interface ApplicationUserService {

    ApplicationUser registerNewUser(ApplicationUser newObject);

    ApplicationUser updateUser(Long id, ApplicationUser object);

    ApplicationUser findByEmail(String email);
}
