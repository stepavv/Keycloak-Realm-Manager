package com.skor.KeycloakRealmManager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.skor.KeycloakRealmManager.config.KeycloakProperties;
import com.skor.KeycloakRealmManager.dto.GroupDto;
import com.skor.KeycloakRealmManager.dto.UserDto;
import com.skor.KeycloakRealmManager.dto.UserWithGroupsDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skor.KeycloakRealmManager.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final AuthService authService;
    private final UserService userService;
    private final KeycloakProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<UserWithGroupsDto> getUserGroups() throws JsonProcessingException {
        String token = authService.getAccessToken();

        List<UserDto> users = userService.getUsers();
        List<UserWithGroupsDto> result = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        for (UserDto user : users) {
            String url = properties.getServerUrl()
                    + "/admin/realms/" + properties.getRealm()
                    + "/users/" + user.getId() + "/groups";

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            List<GroupDto> groups = objectMapper.readValue(
                    response.getBody(),
                    new TypeReference<>() {}
            );

            UserWithGroupsDto dto = UserMapper.INSTANCE.toDto(user, groups);

            result.add(dto);
        }

        ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
        String output = writer.writeValueAsString(result);
        log.info(output);

        return result;
    }
}
