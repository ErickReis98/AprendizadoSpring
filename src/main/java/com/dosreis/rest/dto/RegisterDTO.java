package com.dosreis.rest.dto;

import com.dosreis.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
