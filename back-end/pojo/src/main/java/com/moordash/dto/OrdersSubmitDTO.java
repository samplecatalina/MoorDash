package com.moordash.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {

    private Long addressBookId;

    private int payMethod;

    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    // deliveryStatus 1: immediate delivery  0: choose specific time
    private Integer deliveryStatus;

    private Integer tablewareNumber;

    // tableware status:  1: provide by amount of food  0: choose by user
    private Integer tablewareStatus;

    // packing fee
    private Integer packAmount;

    // total amount
    private BigDecimal amount;
}
