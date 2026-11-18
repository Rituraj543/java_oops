
package com.example.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeemanagement.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    // Add Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        employees.add(employee);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // Get All Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {

        for (Employee e : employees) {
            if (e.getId() == id) {
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update Employee
    // @PutMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") int id,
            @RequestBody Employee updatedEmployee) {

        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getId() == id) {

                employees.set(i, new Employee(
                        id,
                        updatedEmployee.getName(),
                        updatedEmployee.getDepartment(),
                        updatedEmployee.getSalary()));

                return ResponseEntity.ok(employees.get(i));
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

        for (Employee e : employees) {
            if (e.getId() == id) {

                employees.remove(e);

                return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
