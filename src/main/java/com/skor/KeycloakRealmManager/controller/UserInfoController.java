package com.skor.KeycloakRealmManager.controller;

import com.skor.KeycloakRealmManager.dto.UserInfoDto;
import com.skor.KeycloakRealmManager.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-groups")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping
    public List<UserInfoDto> getUserGroups() throws Exception {
        return userInfoService.getUserGroups();
    }
}
