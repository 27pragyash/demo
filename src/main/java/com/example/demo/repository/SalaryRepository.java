package com.example.demo.repository;

import com.example.demo.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Optional<Salary> findByEmployeeId(Long employeeId);
}
