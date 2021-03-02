package com.github.ticoyk.teacherhelperb.configurations;

import javax.annotation.PostConstruct;

import com.github.ticoyk.teacherhelperb.configurations.security.BCryptPassword;
import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.repositories.ApplicationUserRepository;

import org.springframework.stereotype.Component;

@Component
public class SetupData {

    private ApplicationUserRepository userRepository;
    private BCryptPassword bCryptPassword;
    public SetupData(ApplicationUserRepository userRepository, BCryptPassword bCryptPassword){
        this.bCryptPassword = bCryptPassword;
        this.userRepository = userRepository;
    }
    
    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        
        ApplicationUser user1 = new ApplicationUser();
        user1.setName("john");
        user1.setEmail("somemail1@somehost.com");
        user1.setPassword(bCryptPassword.encode("123"));
        userRepository.save(user1);
        
        ApplicationUser user2 = new ApplicationUser();
        user2.setName("tom");
        user2.setEmail("somemail2@somehost.com");
        user2.setPassword("111");
        userRepository.save(user2);
    }

}