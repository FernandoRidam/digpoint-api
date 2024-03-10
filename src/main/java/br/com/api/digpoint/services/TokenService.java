package br.com.api.digpoint.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.digpoint.models.Company;

@Service
public class TokenService {

    private String secret = "adda9c5cd4e4d4a718a05bf8616f3a8fca1ea411";

    public String generateToken(Company company){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("auth")
                .withSubject(company.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;


        } catch (JWTCreationException exception) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }

        public String validateToken(String token){
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);

                return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
            }

            catch (JWTVerificationException exception) {
                return "";
            }
        }

        private Instant getExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }

