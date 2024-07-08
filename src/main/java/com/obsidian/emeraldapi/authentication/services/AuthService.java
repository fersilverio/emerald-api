package com.obsidian.emeraldapi.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.obsidian.emeraldapi.associateuser.repositories.AssociateUserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AssociateUserRepository associateUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return associateUserRepository.findByEmail(username);
    }

}
