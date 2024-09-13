package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * order overview data vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOverViewVO implements Serializable {

    // amount of orders waiting
    private Integer waitingOrders;

    // amount of orders to be delivered
    private Integer deliveredOrders;

    // amount of orders completed
    private Integer completedOrders;

    // amount of orders cancelled
    private Integer cancelledOrders;

    // amount of all orders
    private Integer allOrders;
}
