package com.moordash.mapper;

import com.github.pagehelper.Page;
import com.moordash.dto.GoodsSalesDTO;
import com.moordash.dto.OrdersPageQueryDTO;
import com.moordash.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * Insert order data
     *
     * @param order
     */
    void insert(Orders order);

    /**
     * Query order by order number and user id
     *
     * @param orderNumber
     * @param userId
     * @return
     */
    Orders getByNumberAndUserId(@Param("orderNumber") String orderNumber, @Param("userId") Long userId);

    /**
     * Update order
     *
     * @param orders
     */
    void update(Orders orders);

    /**
     * Paginated conditional query
     *
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * Query order by id
     *
     * @param id
     * @return
     */
    Orders getById(Long id);

    /**
     * Respectively query the number of orders
     * received, to be delivered, and in delivery according to the status
     *
     * @param status
     * @return
     */
    Integer countStatus(Integer status);

    /**
     * Query orders by status and order time
     *
     * @param status
     * @param orderTime
     */
    List<Orders> getByStatusAndOrderTime(@Param("status") Integer status, @Param("orderTime") LocalDateTime orderTime);

    /**
     * Sum turnover according to dynamic conditions
     *
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * Count the amount of orders according to dynamic conditions
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * Query the ranking of goods sales
     *
     * @param begin
     * @param end
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
