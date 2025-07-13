package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Employee;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findById(Long id);

    Optional<Employee> findByUser(AuthRequest request);
}
