package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Salary;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface CombinedService {
    CompletableFuture<Map<String, Object>> getEmployeeAndSalaryById (Long id);
}
