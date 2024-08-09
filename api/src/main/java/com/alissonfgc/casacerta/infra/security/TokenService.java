package com.alissonfgc.casacerta.infra.security;

import com.alissonfgc.casacerta.entities.Client;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Client user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("casacerta-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while authentication");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("casacerta-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationTime() {
        return Instant.now().plusSeconds(7200);
    }
}
