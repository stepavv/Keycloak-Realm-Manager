package com.skor.KeycloakRealmManager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skor.KeycloakRealmManager.config.KeycloakProperties;
import com.skor.KeycloakRealmManager.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthService authService;
    private final KeycloakProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<UserDto> getUsers() throws JsonProcessingException {
        String token = authService.getAccessToken();

        String usersUrl = properties.getServerUrl()
                + "/admin/realms/" + properties.getRealm() + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                usersUrl, HttpMethod.GET, entity, String.class);

        List<UserDto> users = objectMapper.readValue(
                response.getBody(),
                new TypeReference<>() {}
        );

        return users;
    }
}
