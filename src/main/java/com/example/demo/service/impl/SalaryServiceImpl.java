package com.example.demo.service.impl;

import com.example.demo.entity.Salary;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
    @Override
    public Optional<Salary> findByEmployeeId(Long employeeId) {
        try {
            return salaryRepository.findByEmployeeId(employeeId);
        } catch (Exception e) {
            e.printStackTrace(); // will print actual cause of InvocationTargetException
            throw e;
        }
    }

}
