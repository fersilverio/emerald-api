package com.obsidian.emeraldapi.associateuser.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obsidian.emeraldapi.associateuser.dto.AssociateUserDto;
import com.obsidian.emeraldapi.associateuser.dto.UpdateAssociateUserDto;
import com.obsidian.emeraldapi.associateuser.models.AssociateUser;
import com.obsidian.emeraldapi.associateuser.repositories.AssociateUserRepository;
import com.obsidian.emeraldapi.utils.RecordValidation;

@Service
public class AssociateUserService {
    @Autowired
    AssociateUserRepository repository;

    public AssociateUser create(AssociateUserDto dto) {
        try {
            var associateUser = new AssociateUser(
                dto.name(), 
                dto.email(), 
                dto.nickName(), 
                dto.password()
            );

            AssociateUser user = repository.save(associateUser);

            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error creating associate user: " + e.getMessage());
        }
    }

    public List<AssociateUser> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving associate users: " + e.getMessage());
        }
    }

    public AssociateUser findById(Long id) {
       try {
            Optional<AssociateUser> user = repository.findById(id);

            return user.isPresent() ? user.get() : null;

       } catch (Exception e) {
            throw new RuntimeException("Error retrieving associate users: " + e.getMessage());
       }

    }

    public AssociateUser update(Long id, UpdateAssociateUserDto dto) {

        Optional<AssociateUser> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Associate user not found");
        }

        var recordValidation = new RecordValidation<UpdateAssociateUserDto>(dto);
        
        var cleanedDto = recordValidation.removeNullValues(UpdateAssociateUserDto.class, dto);
    
        var userModel = user.get();

        var modelFieldList = AssociateUser.class.getDeclaredFields();

        for (var entry: cleanedDto.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            for (var field: modelFieldList) {
                if (field.getName().equals(key)) {
                    field.setAccessible(true);
                    
                    try {
                         field.set(userModel, value);
                    } catch (IllegalAccessException e) {
                    }
                }
            }
            
        }
        
        return repository.save(userModel);
    }

    public AssociateUser delete(Long id) {
        Optional<AssociateUser> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("Associate user not found");
        }

        repository.delete(user.get());

        return user.get();
        
    }
}