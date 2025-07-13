package com.example.demo.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String username, String role);
    boolean validateToken(String token);
    Claims extractClaims(String token);
}
