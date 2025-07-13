package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<Employee> optionalEmployee = employeeService.findByUser(request);

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        Employee employee = optionalEmployee.get();

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String role = employee.getEmail().equalsIgnoreCase("admin@example.com") ? "ROLE_ADMIN" : "ROLE_EMPLOYEE";
        String token = jwtService.generateToken(employee.getId().toString(), role);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
