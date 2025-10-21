package com.skor.KeycloakRealmManager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skor.KeycloakRealmManager.dto.UserWithGroupsDto;
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
    public List<UserWithGroupsDto> getUserGroups() throws JsonProcessingException {
        return userInfoService.getUserGroups();
    }
}
