package com.skor.KeycloakRealmManager.mapper;

import com.skor.KeycloakRealmManager.dto.GroupDto;
import com.skor.KeycloakRealmManager.dto.UserDto;
import com.skor.KeycloakRealmManager.dto.UserWithGroupsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "groups", source = "groups")
    UserWithGroupsDto toDto(UserDto user, List<GroupDto> groups);

}
