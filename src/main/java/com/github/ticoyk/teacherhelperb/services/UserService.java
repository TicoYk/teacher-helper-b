package com.github.ticoyk.teacherhelperb.services;

import com.github.ticoyk.teacherhelperb.models.User;

public interface UserService {

    User findById(Long id);

    User registerNewUser(User newObject);

    User updateUser(Long id, User object);

}
