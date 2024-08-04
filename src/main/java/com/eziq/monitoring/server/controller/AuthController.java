package com.eziq.monitoring.server.controller;

import com.eziq.monitoring.server.config.RsaKeyProperties;
import com.eziq.monitoring.server.model.Token;
import com.eziq.monitoring.server.model.TokenResponse;
import com.eziq.monitoring.server.service.TokenService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Hidden
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public TokenResponse token(Authentication authentication){
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/token_status")
    public TokenResponse getStatus(@RequestBody Token token) throws JSONException {
        return tokenService.getTokenStatus(token);
    }
}
