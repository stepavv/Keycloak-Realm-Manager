package com.skor.KeycloakRealmManager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {
    String id;
    String name;
    String path;
}
