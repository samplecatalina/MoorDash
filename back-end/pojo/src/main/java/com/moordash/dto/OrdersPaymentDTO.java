package com.moordash.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {

    private String orderNumber;

    private Integer payMethod;

}
