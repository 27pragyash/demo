package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Salary;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.CombinedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class CombinedServiceImpl implements CombinedService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public CompletableFuture<Map<String, Object>> getEmployeeAndSalaryById(Long id) {
        CompletableFuture<Employee> employeeFuture = CompletableFuture.supplyAsync(() ->
                employeeRepository.findById(id).orElse(null));

        CompletableFuture<Salary> salaryFuture = CompletableFuture.supplyAsync(() ->
                salaryRepository.findByEmployeeId(id).orElse(null));

        return CompletableFuture.allOf(employeeFuture, salaryFuture)
                .thenApply(v -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("employee", employeeFuture.join());
                    result.put("salary", salaryFuture.join());
                    return result;
                });
    }

}

