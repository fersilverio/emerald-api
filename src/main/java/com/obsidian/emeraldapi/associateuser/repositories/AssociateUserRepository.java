package com.obsidian.emeraldapi.associateuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obsidian.emeraldapi.associateuser.models.AssociateUser;

public interface AssociateUserRepository extends JpaRepository<AssociateUser, Long> {
}
