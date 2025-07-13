package com.example.demo.controller;

import com.example.demo.service.CombinedService;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/info")
public class CombinedController {

    @Autowired
    private CombinedService combinedService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCombinedInfo(@PathVariable String id) {
        String loggedInEmployeeId = getLoggedInEmployeeId();

        if (!isAdmin() && !loggedInEmployeeId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        CompletableFuture<Map<String, Object>> resultFuture =
                combinedService.getEmployeeAndSalaryById(Long.valueOf(id));

        Map<String, Object> result = resultFuture.join();

        return ResponseEntity.ok(result);
    }

    private String getLoggedInEmployeeId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // JWT subject is employeeId
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
