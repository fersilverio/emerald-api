package com.obsidian.emeraldapi.associateuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.obsidian.emeraldapi.associateuser.models.AssociateUser;

public interface AssociateUserRepository extends JpaRepository<AssociateUser, Long> {
    UserDetails findByEmail(String email);
}
