package com.ousl.examinations.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {


    @Value("${ousl.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ousl.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication, HashMap<String, Object> claims) {
        UserDetailsImplementation userPrincipal = (UserDetailsImplementation) authentication.getPrincipal();
        return generateToken(userPrincipal.getUsername(), claims);
    }

    public String generateToken(String username, HashMap<String, Object> claims) {
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .and()
                .signWith(getKey())
                .compact();

    }


    private SecretKey getKey() {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] keyBytes = sha512.digest(jwtSecret.getBytes(StandardCharsets.UTF_8));
            return Keys.hmacShaKeyFor(keyBytes); // 64-byte key suitable for HS512
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate key", e);
        }
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
