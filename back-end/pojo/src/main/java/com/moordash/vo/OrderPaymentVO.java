package com.moordash.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentVO implements Serializable {

    private String nonceStr; // random string
    private String paySign; // signatrue
    private String timeStamp; // timestamp
    private String signType; // type of signature algorithm
    private String packageStr; // prepay_id returned by the unified order api

}
