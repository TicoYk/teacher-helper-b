package com.github.ticoyk.teacherhelperb.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @RequestMapping("/api/test")
    public String test() {
        return "Hello, Test!";
    }
}
