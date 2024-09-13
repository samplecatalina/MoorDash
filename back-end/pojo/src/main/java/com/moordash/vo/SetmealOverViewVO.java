package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * overview of setmeal
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealOverViewVO implements Serializable {
    // amount of sold setmeal
    private Integer sold;

    // amount of discontinued setmeal
    private Integer discontinued;
}
