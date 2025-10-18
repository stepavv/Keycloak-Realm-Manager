package com.skor.KeycloakRealmManager.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto extends UserDto{
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private List<GroupDto> groups;
}
