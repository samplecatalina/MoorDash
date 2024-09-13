package com.moordash.mapper;

import com.moordash.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDetailMapper {
    /**
     * Batch insert order detail data
     *
     * @param orderDetailList
     */
    void insertBatch(ArrayList<OrderDetail> orderDetailList);

    /**
     * Query order details by order id
     *
     * @param ordersId
     * @return
     */
    List<OrderDetail> getByOrderId(Long ordersId);
}
