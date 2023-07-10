package com.dosreis.rest.dto;

import com.dosreis.domain.enums.UserRole;

public record ResgisterDTO(String login, String password, UserRole role) {

}
