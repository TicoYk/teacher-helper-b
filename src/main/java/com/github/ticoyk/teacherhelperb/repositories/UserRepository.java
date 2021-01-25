package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
