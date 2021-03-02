package com.github.ticoyk.teacherhelperb.services;

import com.github.ticoyk.teacherhelperb.configurations.security.BCryptPassword;
import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.repositories.ApplicationUserRepository;

import org.springframework.stereotype.Service;

// TO DO
@Service
public class ApplicationUserServiceImp implements ApplicationUserService{

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPassword bCryptPassword;

    public ApplicationUserServiceImp(ApplicationUserRepository applicationUserRepository, BCryptPassword bCryptPassword){
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPassword = bCryptPassword;
    }

    public ApplicationUser registerNewUser(ApplicationUser newObject) {
        newObject.setEmail(newObject.getEmail());
        newObject.setPassword(bCryptPassword.encode(newObject.getPassword()));
        return applicationUserRepository.save(newObject);
    }

    public ApplicationUser updateUser(Long id, ApplicationUser object) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ApplicationUser findByEmail(String email) {
        return applicationUserRepository.findByEmail(email.toLowerCase());
    }

}
