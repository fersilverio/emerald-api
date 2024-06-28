package com.obsidian.emeraldapi.associateuser.controllers;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.obsidian.emeraldapi.associateuser.dto.AssociateUserDto;
import com.obsidian.emeraldapi.associateuser.models.AssociateUser;
import com.obsidian.emeraldapi.associateuser.repositories.AssociateUserRepository;

import jakarta.validation.Valid;

@Controller
public class AssociateUserController {
    @Autowired
    AssociateUserRepository associateUserRepository;

    @PostMapping("/associate-users")
    public ResponseEntity<AssociateUser> createAssociateUser(@RequestBody @Valid AssociateUserDto dto) {
        var associateUser = new AssociateUser();
        BeanUtils.copyProperties(dto, associateUser);
        associateUser.setCreateDate(new Date());
        associateUser.setUpdateDate(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body((associateUserRepository.save(associateUser)));
    }
    
}
