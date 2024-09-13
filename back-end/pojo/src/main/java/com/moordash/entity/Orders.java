package com.moordash.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * order
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {

    /**
     * order status: 1 pending payment 2 to be confirmed 3 confirmed 4 delivery in progress 5 completed 6 cancelled
     */
    public static final Integer PENDING_PAYMENT = 1;
    public static final Integer TO_BE_CONFIRMED = 2;
    public static final Integer CONFIRMED = 3;
    public static final Integer DELIVERY_IN_PROGRESS = 4;
    public static final Integer COMPLETED = 5;
    public static final Integer CANCELLED = 6;

    /**
     * payment status 0 unpaid 1 paid 2 refund
     */
    public static final Integer UN_PAID = 0;
    public static final Integer PAID = 1;
    public static final Integer REFUND = 2;

    private static final long serialVersionUID = 1L;

    private Long id;

    // order number
    private String number;

    // order status
    // 1 pending payment 2 to be confirmed 3 confirmed
    // 4 delivery in progress 5 completed 6 cancelled 7 refund
    private Integer status;

    // order by user id
    private Long userId;

    // address id
    private Long addressBookId;

    private LocalDateTime orderTime;

    private LocalDateTime checkoutTime;

    // payment method 1 WeChat, 2 Alipay
    private Integer payMethod;

    // payment status 0 unpaid 1 paid 2 refund
    private Integer payStatus;

    // amount received
    private BigDecimal amount;

    private String remark;

    private String userName;

    // phone number
    private String phone;

    private String address;

    private String consignee;

    private String cancelReason;

    private String rejectionReason;

    private LocalDateTime cancelTime;

    private LocalDateTime estimatedDeliveryTime;

    // delivery status 1 send out immediately 0 choose specific time
    private Integer deliveryStatus;

    private LocalDateTime deliveryTime;

    // packing fee
    private int packAmount;

    // amount of tableware
    private int tablewareNumber;

    // tableware quantity status 1 provided according to the number of meals 0 choose the specific quantity
    private Integer tablewareStatus;
}
