package com.skor.KeycloakRealmManager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skor.KeycloakRealmManager.dto.UserDto;
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
    public String createUser(@RequestBody UserDto user) throws JsonProcessingException {
        return userCreateService.createUser(user);
    }

}
