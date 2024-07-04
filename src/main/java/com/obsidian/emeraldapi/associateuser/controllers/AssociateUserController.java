package com.obsidian.emeraldapi.associateuser.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.obsidian.emeraldapi.associateuser.dto.AssociateUserDto;
import com.obsidian.emeraldapi.associateuser.dto.UpdateAssociateUserDto;
import com.obsidian.emeraldapi.associateuser.models.AssociateUser;
import com.obsidian.emeraldapi.associateuser.services.AssociateUserService;

import jakarta.validation.Valid;

@Controller
public class AssociateUserController {
    @Autowired
    AssociateUserService service;

    @PostMapping("/associate-users")
    public ResponseEntity<AssociateUser> createAssociateUser(@RequestBody @Valid AssociateUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/associate-users")
    public ResponseEntity<List<AssociateUser>> getAllAssociateUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/associate-users/{id}")
    public ResponseEntity<AssociateUser> getAssociateUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PatchMapping("/associate-users/{id}")
    public ResponseEntity<AssociateUser> updateAssociateUser(@PathVariable Long id, @RequestBody @Valid UpdateAssociateUserDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @DeleteMapping("/associate-users/{id}")
    public ResponseEntity<AssociateUser> deleteAssociateUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
