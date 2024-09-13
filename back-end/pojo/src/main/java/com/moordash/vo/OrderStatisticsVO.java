package com.moordash.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderStatisticsVO implements Serializable {

    // the amount of orders to be confirmed
    private Integer toBeConfirmed;

    // the amount of orders confirmed and to be delivered
    private Integer confirmed;

    // the amount of orders that are being delivered
    private Integer deliveryInProgress;
}
