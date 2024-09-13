package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * data overview
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataVO implements Serializable {

    private Double turnover;

    private Integer validOrderCount;

    private Double orderCompletionRate;

    private Double unitPrice; // average price of an order

    private Integer newUsers; // number of new users

}
