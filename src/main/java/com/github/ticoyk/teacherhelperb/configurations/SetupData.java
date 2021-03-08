package com.github.ticoyk.teacherhelperb.configurations;

import javax.annotation.PostConstruct;

import com.github.ticoyk.teacherhelperb.configurations.security.BCryptPassword;
import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.repositories.ApplicationUserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SetupData {

    private ApplicationUserRepository userRepository;
    private BCryptPassword bCryptPassword;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public SetupData(ApplicationUserRepository userRepository, BCryptPassword bCryptPassword){
        this.bCryptPassword = bCryptPassword;
        this.userRepository = userRepository;
    }
    
    @PostConstruct
    public void init() {
        if(activeProfile.equals("dev")){
            initUsers();
        }
    }

    private void initUsers() {
        
        ApplicationUser user1 = new ApplicationUser();
        user1.setName("joao");
        user1.setEmail("joao@somemail.com");
        user1.setPassword(bCryptPassword.encode("password"));
        userRepository.save(user1);
        
        ApplicationUser user2 = new ApplicationUser();
        user2.setName("pedro");
        user2.setEmail("pedro@somemail.com");
        user2.setPassword(bCryptPassword.encode("password"));
        userRepository.save(user2);
    }

}