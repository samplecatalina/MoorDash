package com.moordash.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * order detail
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long orderId;

    private Long dishId;

    private Long setmealId;

    private String dishFlavor;

    // amount of dishes
    private Integer number;

    // amount of money
    private BigDecimal amount;

    private String image;
}
