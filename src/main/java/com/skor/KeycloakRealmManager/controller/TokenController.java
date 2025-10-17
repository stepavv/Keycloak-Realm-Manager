package com.skor.KeycloakRealmManager.controller;

import com.skor.KeycloakRealmManager.service.KeycloakAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final KeycloakAuthService keycloakAuthService;

    @GetMapping("/token")
    public String getToken() {
        return keycloakAuthService.getAccessToken();
    }
}

