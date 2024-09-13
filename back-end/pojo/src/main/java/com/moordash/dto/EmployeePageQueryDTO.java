package com.moordash.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    // employee name
    private String name;

    // page number
    private int page;

    // number of records per page
    private int pageSize;

}
