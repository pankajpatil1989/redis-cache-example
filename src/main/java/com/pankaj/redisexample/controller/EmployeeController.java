package com.pankaj.redisexample.controller;

import com.pankaj.redisexample.model.Employee;
import com.pankaj.redisexample.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        log.info("All Employee fetching from database::getAllEmployees() called.");
        return employeeService.getAllEmployees();
    }

    @GetMapping("employees/{employeeId}")
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee findEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
        log.info("Employee fetching from database::findEmployeeById() called: " + employeeId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        log.info("Adding Employee to database::addEmployee() called.");
        employeeService.addEmployee(employee);
        return employee;
    }

    @PutMapping("employees/{employeeId}")
    public Employee updateEmployee(@PathVariable(value = "employeeId") Integer employeeId,
                                                   @RequestBody Employee employeeDetails) {
        log.info("Employee updated to database::updateEmployee() called: " + employeeId);
        employeeService.updateEmployee(employeeId,employeeDetails);
        return employeeDetails;
    }


    @DeleteMapping("employees/{id}")
    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        log.info("Employee deleted from database::deleteEmployee() called: " + employeeId);
        employeeService.deleteEmployee(employeeId);
    }


}
