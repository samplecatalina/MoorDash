package com.moordash.mapper;

import com.github.pagehelper.Page;
import com.moordash.annotation.AutoFill;
import com.moordash.dto.EmployeePageQueryDTO;
import com.moordash.entity.Employee;
import com.moordash.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * Query an employee by username
     *
     * @param username
     * @return
     */
    Employee getByUsername(@Param("username") String username);

    /**
     * Insert an employee
     *
     * @param employee
     */
    @AutoFill(OperationType.INSERT)
    void insert(Employee employee);

    /**
     * Query employees with pagination
     *
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Activate/deactivate an employee account, and update the employee information
     *
     * @param employee
     */
    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    /**
     * Query an employee by ID
     *
     * @param id
     * @return
     */
    Employee getById(@Param("id") Long id);

}
