package com.obsidian.emeraldapi.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.obsidian.emeraldapi.associateuser.models.AssociateUser;
import com.obsidian.emeraldapi.associateuser.repositories.AssociateUserRepository;
import com.obsidian.emeraldapi.authentication.dto.AuthDto;
import com.obsidian.emeraldapi.authentication.dto.LoginResponseDto;
import com.obsidian.emeraldapi.authentication.dto.RegisterDto;
import com.obsidian.emeraldapi.infrastructure.security.TokenService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AssociateUserRepository associateUserRepository;

    @Autowired
    TokenService tokenService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        
        System.out.println(this.authManager);

        var auth = this.authManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((AssociateUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    @SuppressWarnings("rawtypes")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
        if (this.associateUserRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        AssociateUser newUser = new AssociateUser(
            data.name(), 
            data.email(), 
            data.nickName(), 
            encryptedPassword,
            data.role()
        );
            

        this.associateUserRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}