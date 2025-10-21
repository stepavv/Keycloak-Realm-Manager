package com.skor.KeycloakRealmManager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skor.KeycloakRealmManager.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectWriter;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final KeycloakProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public String getAccessToken() throws JsonProcessingException {
        String tokenUrl = properties.getServerUrl() + "/realms/" + properties.getRealm()
                + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials" +
                "&client_id=" + properties.getClientId() +
                "&client_secret=" + properties.getClientSecret();

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get token: " + response);
        }

        JsonNode json = mapper.readTree(response.getBody());
        return json.get("access_token").asText();
    }
}
