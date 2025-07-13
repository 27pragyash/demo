package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Mocked authentication
        String role = request.getUsername().equals("admin") ? "ROLE_ADMIN" : "ROLE_EMPLOYEE";
        String token = jwtService.generateToken(request.getUsername(), role);
        return ResponseEntity.ok(new AuthResponse());
    }
}