package com.dosreis.rest.dto;

import com.dosreis.domain.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
