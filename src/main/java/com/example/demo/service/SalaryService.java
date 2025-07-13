package com.example.demo.service;

import com.example.demo.entity.Salary;

import java.util.Optional;

public interface SalaryService {
    Optional<Salary> findByEmployeeId(Long employeeId);
}
