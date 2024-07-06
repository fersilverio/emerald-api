package com.obsidian.emeraldapi.authentication.dto;

import com.obsidian.emeraldapi.associateuser.enums.UserRole;

public record RegisterDto(String email, String password, UserRole role, String name, String nickName) {
}
