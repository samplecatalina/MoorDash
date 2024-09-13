package com.moordash.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * setmeal - dish relation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long setmealId;

    private Long dishId;

    // dish name (redundant field)
    private String name;

    // dish original price
    private BigDecimal price;

    // amount of dish in setmeal
    private Integer copies;
}
