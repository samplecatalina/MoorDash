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
public class UserReportVO implements Serializable {

    // date list, sperated by comma, e.g. 2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    // list of total number of users, sperated by comma, e.g. 200,210,220
    private String totalUserList;

    // list of new users, sperated by comma, e.g. 20,21,10
    private String newUserList;

}
