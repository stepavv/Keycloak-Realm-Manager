package com.skor.KeycloakRealmManager.service;

import com.skor.KeycloakRealmManager.config.KeycloakProperties;
import com.skor.KeycloakRealmManager.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;



@Service
@RequiredArgsConstructor
public class UserCreateService {
    private final AuthService authService;
    private final KeycloakProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String createUser(UserCreateDto user) throws Exception {
        String token = authService.getAccessToken();

        String url = properties.getServerUrl()
                + "/admin/realms/" + properties.getRealm() + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String body = objectMapper.writeValueAsString(user);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("User created: " + user.getUsername());
            return "User created successfully: " + user.getUsername();
        } else {
            System.err.println("Failed to create user: " + response.getBody());
            return "Failed to create user: " + response.getStatusCode();
        }

    }
}
