package com.skor.KeycloakRealmManager.controller;


import com.skor.KeycloakRealmManager.dto.UserDto;
import com.skor.KeycloakRealmManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    private List<UserDto> getUsers() throws Exception {
        return userService.getUsers();
    }
}
