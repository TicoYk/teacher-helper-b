package com.github.ticoyk.teacherhelperb.controllers.rest;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.utils.JwtUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private JwtUtil jwtUtil;

    public UserController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @GetMapping("/api/user")
    @ResponseBody
    public ApplicationUser getCurrentUser(@RequestHeader("Authorization") String token) {
        return jwtUtil.parseTokenToUser(token);
    }
}
