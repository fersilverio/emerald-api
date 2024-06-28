package com.obsidian.emeraldapi.associateuser.dto;

import jakarta.validation.constraints.NotBlank;

public record AssociateUserDto(
    @NotBlank String name,
    @NotBlank String email,
    @NotBlank String nickName,
    @NotBlank String password
) {
}
