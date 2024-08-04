package com.eziq.monitoring.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SecurityRequirement(name = "eziq-monitoring")
@RequestMapping("/api")
public class Controller {

    @GetMapping("/status")
    public String getStatus(Principal principal){
        System.out.println(principal.getName());
        return "I am alive and running with user : " + principal.getName();
    }
}
