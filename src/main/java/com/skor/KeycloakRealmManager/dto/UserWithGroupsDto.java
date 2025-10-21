package com.skor.KeycloakRealmManager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;



@Data
@EqualsAndHashCode(callSuper = true)
public class UserWithGroupsDto extends UserDto {
    private List<GroupDto> groups;
}
