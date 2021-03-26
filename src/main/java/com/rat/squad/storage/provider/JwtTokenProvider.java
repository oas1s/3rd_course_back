package com.rat.squad.storage.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {
    /**
     * JwtTokenProvider является реализацией интерфейса TokenProvider, являющегося декоратором
     * Слабая связь помогает нам заменить реализацию валидации токена
     * Поменяв реализацию в 1 месте, она поменяется во всем проекте
     */
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;
    private Algorithm algorithm;

    @PostConstruct
    protected void init() {
        algorithm = Algorithm.HMAC256(secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getExpiresAt().after(new Date());
        } catch (IllegalArgumentException | JWTVerificationException exception) {
            throw new JWTVerificationException("Expired or invalid JWT token");
        }
    }
}