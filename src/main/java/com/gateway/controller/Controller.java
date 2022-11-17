package com.gateway.controller;


import com.nimbusds.jwt.JWT;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class Controller {
    @GetMapping("/test")
    public Principal test( Principal principal) {
        return principal;
    }
}