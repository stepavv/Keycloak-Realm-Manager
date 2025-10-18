package com.skor.KeycloakRealmManager.controller;

import com.skor.KeycloakRealmManager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/token")
    public String getToken() throws Exception {
        return  authService.getAccessToken();
    }

}
