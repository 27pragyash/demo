package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    private String id;
    private String name;
    private String department;
    private String email;
}
