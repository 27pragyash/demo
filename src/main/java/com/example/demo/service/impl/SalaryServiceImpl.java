package com.example.demo.service.impl;

import com.example.demo.entity.Salary;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
    @Override
    public Optional<Salary> findByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }
}
