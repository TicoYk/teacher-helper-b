package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long>{
    ApplicationUser findByName(String name);
    ApplicationUser findByEmail(String email);
}
