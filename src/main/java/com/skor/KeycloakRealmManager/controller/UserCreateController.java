package com.skor.KeycloakRealmManager.controller;

import com.skor.KeycloakRealmManager.dto.UserCreateDto;
import com.skor.KeycloakRealmManager.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserCreateController {

    private final UserCreateService userCreateService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserCreateDto user) throws Exception {
        return userCreateService.createUser(user);
    }

}
