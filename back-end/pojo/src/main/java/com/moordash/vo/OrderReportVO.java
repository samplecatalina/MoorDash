package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderReportVO implements Serializable {

    // date list, separated by comma, e.g. 2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    // order count per day, separated by comma, e.g. 260,210,215
    private String orderCountList;

    // valid order count per day, separated by comma, e.g. 200,180,185
    private String validOrderCountList;

    // total order count
    private Integer totalOrderCount;

    // valid order count
    private Integer validOrderCount;

    // order completion rate
    private Double orderCompletionRate;

}
