package com.ousl.examinations.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @GetMapping("/get-session-id")
    public String greet(HttpServletRequest request) {
        return "Welcome "+request.getSession().getId();
    }

}
