package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>{
    ApplicationUser findByName(String name);
    ApplicationUser findByEmail(String email);
}
