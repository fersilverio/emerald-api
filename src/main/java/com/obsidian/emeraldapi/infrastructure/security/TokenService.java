package com.obsidian.emeraldapi.infrastructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.obsidian.emeraldapi.associateuser.models.AssociateUser;

@Service
public class TokenService {
   @Value("${api.security.token.secret}")
   private String secret;
   
   public String generateToken(AssociateUser user) {
    try {
        Algorithm alg = Algorithm.HMAC256(secret);

        String token = JWT.create()
            .withIssuer("auth-api")
            .withSubject(user.getEmail())
            .withExpiresAt(this.genExpirationDate())
            .sign(alg);

        return token;
    } catch (JWTCreationException exception) {
        throw new RuntimeException("Error generating token", exception);
    }
   }

   public String validateToken(String token) {
    try {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.require(alg)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
    } catch (JWTCreationException exception) {
        return "";
    }
}

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}