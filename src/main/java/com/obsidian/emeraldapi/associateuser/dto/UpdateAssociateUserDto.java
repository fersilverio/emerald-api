package com.obsidian.emeraldapi.associateuser.dto;

public record UpdateAssociateUserDto(
    String name,
    String email,
    String nickName,
    String password,
    Integer numberOfCards,
    Integer level,
    Integer rank,
    String clan
) {
}
