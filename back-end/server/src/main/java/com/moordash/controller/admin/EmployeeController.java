package com.moordash.controller.admin;

import com.moordash.constant.JwtClaimsConstant;
import com.moordash.dto.EmployeeDTO;
import com.moordash.dto.EmployeeLoginDTO;
import com.moordash.dto.EmployeePageQueryDTO;
import com.moordash.entity.Employee;
import com.moordash.properties.JwtProperties;
import com.moordash.result.PageResult;
import com.moordash.result.Result;
import com.moordash.service.EmployeeService;
import com.moordash.utils.JwtUtil;
import com.moordash.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee management
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee Related APIs")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Login
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee Logging in：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        // After successful login, generate a JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * Logout
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * Add new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new employee")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Add new employee: {}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * Paginated employee query
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Paginated employee query")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Paginated employee query by parameters of：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Activate/deactivate an employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Activate or deactivate an employee account")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Activate/deactivate an employee account ：{}，{}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * Get employee information by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get employee information by ID")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("Query employee info by id: {}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * Edit employee information
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Edit employee information")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Edit employee information：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     * When a client sends a request to your Spring Boot application,
     * it can include data in various parts of the HTTP request, such as the URL (query parameters), headers, or body.
     * JSON data is typically sent in the body of the HTTP request (e.g., in a POST request).
     * The @RequestBody annotation tells Spring that the data to be bound to the parameter (in this case, your DTO)
     * is located in the body of the request.
     */
}
