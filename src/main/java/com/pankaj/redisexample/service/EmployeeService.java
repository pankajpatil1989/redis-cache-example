package com.pankaj.redisexample.service;

import com.pankaj.redisexample.excpetion.ResourceNotFoundException;
import com.pankaj.redisexample.model.Employee;
import com.pankaj.redisexample.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        log.info("All Employee fetching from database::getAllEmployees() called.");
        employees = employeeRepository.findAll();
        return employees;
    }


    public Employee findEmployeeById(Integer employeeId) {
        log.info("Employee fetching from database::findEmployeeById() called: " + employeeId);
        Optional<Employee> employee = null;
        employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return employee.get();
    }

    public Employee addEmployee(Employee employee) {
        log.info("Adding Employee to database::addEmployee() called.");
        return  employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (!employee.isPresent()) {
            throw new ResourceNotFoundException();
        }

        Employee updatingEmployee = new Employee();
        updatingEmployee.setName(employeeDetails.getName());
        log.info("Employee updated to database::updateEmployee() called: " + employeeId);
        return employeeRepository.save(updatingEmployee);
    }


    public void deleteEmployee(Integer employeeId) {
        log.info("Employee deleted from database::deleteEmployee() called: " + employeeId);
        employeeRepository.deleteById(employeeId);
    }

}
