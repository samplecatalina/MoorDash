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
public class SalesTop10ReportVO implements Serializable {

    // list of product names, separated by commas, e.g. Yu Xiang Rou Si, Gong Bao Ji Ding, Shui Zhu Yu
    private String nameList;

    // list of sales numbers, separated by commas, e.g. 260,215,200
    private String numberList;

}
