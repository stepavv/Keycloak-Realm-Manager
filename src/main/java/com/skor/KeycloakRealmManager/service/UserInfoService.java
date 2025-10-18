package com.skor.KeycloakRealmManager.service;

import com.skor.KeycloakRealmManager.config.KeycloakProperties;
import com.skor.KeycloakRealmManager.dto.GroupDto;
import com.skor.KeycloakRealmManager.dto.UserDto;
import com.skor.KeycloakRealmManager.dto.UserInfoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final AuthService authService;
    private final UserService userService;
    private final KeycloakProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<UserInfoDto> getUserGroups() throws Exception {
        String token = authService.getAccessToken();

        List<UserDto> users = userService.getUsers();
        List<UserInfoDto> result = new ArrayList<>();

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
                    new TypeReference<List<GroupDto>>() {}
            );

            UserInfoDto dto = new UserInfoDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            dto.setEnabled(user.isEnabled());
            dto.setGroups(groups);

            result.add(dto);
        }

        System.out.println("                   ");
        System.out.println("=== USERS INFO ===");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        System.out.println("==================");

        return result;
    }
}
