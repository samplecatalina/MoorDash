package com.moordash.dto;

import com.moordash.entity.OrderDetail;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {

    private Long id;

    // order number
    private String number;

    // order status 1 unpaid, 2 to be delivered, 3 delivered, 4 completed, 5 cancelled
    private Integer status;

    // user id of the order
    private Long userId;

    // address id
    private Long addressBookId;

    // order time
    private LocalDateTime orderTime;

    // checkout time
    private LocalDateTime checkoutTime;

    // payment method 1 WeChat, 2 Alipay
    private Integer payMethod;

    // amount received
    private BigDecimal amount;

    // remarks
    private String remark;

    // username
    private String userName;

    // phone number
    private String phone;

    private String address;

    // consignee
    private String consignee;

    private List<OrderDetail> orderDetails;

}
