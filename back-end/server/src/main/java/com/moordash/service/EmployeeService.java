package com.moordash.service;

import com.moordash.dto.EmployeeDTO;
import com.moordash.dto.EmployeeLoginDTO;
import com.moordash.dto.EmployeePageQueryDTO;
import com.moordash.entity.Employee;
import com.moordash.result.PageResult;

public interface EmployeeService {

    /**
     * Log in an employee
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add in a new employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Query employees with pagination
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Activate or deactivate an employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query employee information by ID
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * Edit employee information
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
