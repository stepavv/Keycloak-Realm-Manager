package com.skor.KeycloakRealmManager.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserCreateDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private boolean emailVerified;
}
