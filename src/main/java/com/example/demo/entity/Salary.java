package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String currency;

    private String grade;

    @Column(name = "employee_id")
    private Long employeeId;
}
