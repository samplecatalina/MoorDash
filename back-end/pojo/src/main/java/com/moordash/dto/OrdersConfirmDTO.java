package com.moordash.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {

    private Long id;

    // order status: 1 to be paid 2 to be received 3 received 4 delivering 5 completed 6 cancelled 7 refunded
    private Integer status;

}
