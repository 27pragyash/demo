package com.example.demo.controller;

import com.example.demo.entity.Salary;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getSalary(@PathVariable Long employeeId) {
        String loggedInEmployeeId = getLoggedInEmployeeId();

        // Only allow access if ADMIN or requesting own salary
        if (isAdmin() || loggedInEmployeeId.equals(String.valueOf(employeeId))) {
            Optional<Salary> salary = salaryService.findByEmployeeId(employeeId);
            return salary.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    private String getLoggedInEmployeeId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // employeeId is used as subject in JWT
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}