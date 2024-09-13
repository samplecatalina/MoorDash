package com.moordash.vo;

import com.moordash.entity.OrderDetail;
import com.moordash.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO extends Orders implements Serializable {

    // order dish names
    private String orderDishes;

    // order dish details
    private List<OrderDetail> orderDetailList;

}
