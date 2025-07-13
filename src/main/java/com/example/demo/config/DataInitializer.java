package com.example.demo.config;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Salary;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(EmployeeRepository employeeRepo, SalaryRepository salaryRepo, PasswordEncoder encoder) {
        return args -> {
            salaryRepo.deleteAll();
            employeeRepo.deleteAll();

            // Create employees
            Employee e1 = employeeRepo.save(new Employee(null, "John Doe", "Engineering", "john.doe@example.com", encoder.encode("employee1")));
            Employee e2 = employeeRepo.save(new Employee(null, "Jane Smith", "Human Resources", "jane.smith@example.com", encoder.encode("employee2")));
            Employee e3 = employeeRepo.save(new Employee(null, "Admin User", "Management", "admin@example.com", encoder.encode("admin")));
            Employee e4 = employeeRepo.save(new Employee(null, "Bob Marley", "Design", "bob@example.com", encoder.encode("employee3")));

            // Create salaries
            salaryRepo.save(new Salary(null, 75000.00, "INR", "A1", e1.getId()));
            salaryRepo.save(new Salary(null, 68000.00, "INR", "B1", e2.getId()));
            salaryRepo.save(new Salary(null, 120000.00, "INR", "Admin", e3.getId()));
            salaryRepo.save(new Salary(null, 55000.00, "INR", "C1", e4.getId()));
        };
    }
}
