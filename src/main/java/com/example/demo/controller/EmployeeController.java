package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        String loggedInEmployeeId = getLoggedInEmployeeId();

        if (isAdmin() || loggedInEmployeeId.equals(id)) {

            return ResponseEntity.ok(employeeService.findById(Long.valueOf(id)));
        } else {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private String getLoggedInEmployeeId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // this is employeeId because of setSubject(employeeId)
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}