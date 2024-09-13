package com.moordash.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // type: 1 dish category 2 set meal category
    private Integer type;

    // category name
    private String name;

    // sorted order
    private Integer sort;

    // category status 0: disable 1: enable
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    // user who created this category
    private Long createUser;

    // user who updated this category
    private Long updateUser;
}
