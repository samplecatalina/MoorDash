package com.moordash.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.moordash.constant.MessageConstant;
import com.moordash.constant.PasswordConstant;
import com.moordash.constant.StatusConstant;
import com.moordash.context.BaseContext;
import com.moordash.dto.EmployeeDTO;
import com.moordash.dto.EmployeeLoginDTO;
import com.moordash.dto.EmployeePageQueryDTO;
import com.moordash.entity.Employee;
import com.moordash.exception.AccountLockedException;
import com.moordash.exception.AccountNotFoundException;
import com.moordash.exception.PasswordErrorException;
import com.moordash.mapper.EmployeeMapper;
import com.moordash.result.PageResult;
import com.moordash.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Employee login
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 1. Query the employee by username
        Employee employee = employeeMapper.getByUsername(username);

        // 2. Handle various exceptions (username does not exist, password is incorrect, account is locked)
        if (employee == null) {
            // Account does not exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // Encrypt the password and compare it with the password in the database
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            // Wrong password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // Account is locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 3. Return the employee entity
        return employee;
    }

    /**
     * Add a new employee
     * @param employeeDTO
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        // Copy the properties of the source object to the target object
        BeanUtils.copyProperties(employeeDTO, employee);

        // Set the account status, the default is normal status, 1 means normal, 0 means locked
        employee.setStatus(StatusConstant.ENABLE);

        // Set the encrypted password; default password is 123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        // set the current record's creation time and modification time
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // Get user information through ThreadLocal
        Long currentId = BaseContext.getCurrentId();

        // Set the current record's creator id and modifier id
        employee.setCreateUser(currentId);
        employee.setUpdateUser(currentId);

        employeeMapper.insert(employee);
    }

    /**
     * Pagination query employees, implemented with PageHelper plugin
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // Start the pagination query
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        // Used the query method in EmployeeMapper xml,
        // since a dynamic query in annotation would be complex and not recommended
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total, records);
    }

    /**
     * Activate / deactivate an employee account
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * Query employee information by ID
     * @param id
     * @return
     */
    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("****");
        return employee;
    }

    /**
     * Edit employee information
     * @param employeeDTO
     */
    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());

        // call the sql in the EmployeeMapper.xml to update the database
        employeeMapper.update(employee);
    }

}
