package com.moordash.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    // page number
    private int page;

    private int pageSize;

    private String name;

    // type of category 1 for dish category 2 for set meal category
    private Integer type;

}
