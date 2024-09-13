package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * dish overview vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishOverViewVO implements Serializable {
    // amount of dishes on sale
    private Integer sold;

    // amount of dishes discontinued
    private Integer discontinued;
}
