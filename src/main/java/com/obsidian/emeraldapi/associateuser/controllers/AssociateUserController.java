package com.obsidian.emeraldapi.associateuser.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
import com.obsidian.emeraldapi.associateuser.repositories.AssociateUserRepository;

import jakarta.validation.Valid;

@Controller
public class AssociateUserController {
    @Autowired
    AssociateUserRepository associateUserRepository;

    @PostMapping("/associate-users")
    public ResponseEntity<AssociateUser> createAssociateUser(@RequestBody @Valid AssociateUserDto dto) {
        var associateUser = new AssociateUser(
            dto.name(), 
            dto.email(), 
            dto.nickName(), 
            dto.password()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body((associateUserRepository.save(associateUser)));
    }

    @GetMapping("/associate-users")
    public ResponseEntity<List<AssociateUser>> getAllAssociateUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(associateUserRepository.findAll());
    }

    @GetMapping("/associate-users/{id}")
    public ResponseEntity<Object> getAssociateUserById(@PathVariable Long id) {
        Optional<AssociateUser> user = associateUserRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associate user not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PatchMapping("/associate-users/{id}")
    public ResponseEntity<Object> updateAssociateUser(@PathVariable Long id, @RequestBody @Valid UpdateAssociateUserDto dto) {
        Optional<AssociateUser> user = associateUserRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associate user not found");
        }
        
        var userModel = user.get();

        BeanUtils.copyProperties(dto, userModel);

        return ResponseEntity.status(HttpStatus.OK).body(associateUserRepository.save(userModel));
    }

    @DeleteMapping("/associate-users/{id}")
    public ResponseEntity<Object> deleteAssociateUser(@PathVariable Long id) {
        Optional<AssociateUser> user = associateUserRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associate user not found");
        }

        associateUserRepository.delete(user.get());

        return ResponseEntity.status(HttpStatus.OK).body("Associate user successfully deleted");
    }
}
