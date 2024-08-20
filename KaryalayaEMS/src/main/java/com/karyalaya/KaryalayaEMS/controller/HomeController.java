package com.karyalaya.KaryalayaEMS.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping
    public String welcome(HttpServletRequest request) {
        return "Hello World! => " + request.getSession().getId();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // to using this method level security @EnableMethodSecurity annotation should be on config class
    //else we've to write       .requestMatchers("/admin/**").hasAnyRole("ADMIN") in config
    public String adminOnlyMethod() {
        return "This method is only accessible if your role is admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userOnlyMethod() {
        return "This method is only accessible if your role is user";
    }

}
