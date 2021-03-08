package com.github.ticoyk.teacherhelperb.controllers.rest;

import com.github.ticoyk.teacherhelperb.configurations.security.SecurityConstants;
import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.services.ApplicationUserServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    ApplicationUserServiceImp applicationUserServiceImp;

    AuthController(ApplicationUserServiceImp applicationUserServiceImp){
        this.applicationUserServiceImp = applicationUserServiceImp;
    }

    @PostMapping(SecurityConstants.REGISTER_URL)
    public ResponseEntity<Object> signUp(@RequestBody ApplicationUser user){
        return new ResponseEntity<>(this.applicationUserServiceImp.registerNewUser(user), HttpStatus.CREATED);
    }
}
