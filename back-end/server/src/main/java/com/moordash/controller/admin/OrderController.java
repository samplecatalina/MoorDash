package com.moordash.controller.admin;

import com.moordash.dto.OrdersCancelDTO;
import com.moordash.dto.OrdersPageQueryDTO;
import com.moordash.dto.OrdersRejectionDTO;
import com.moordash.result.PageResult;
import com.moordash.result.Result;
import com.moordash.service.OrderService;
import com.moordash.vo.OrderStatisticsVO;
import com.moordash.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Order management
 */
@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "Order management")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Order search
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("Order search")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Statistics of the number of orders in each state
     *
     * @return
     */
    @GetMapping("/statistics")
    @ApiOperation("Statistics of the number of orders in each state")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * Query order details
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("Query order details")
    public Result<OrderVO> details(Long id) {
        OrderVO details = orderService.details(id);
        return Result.success(details);
    }

    /**
     * Confirm an order
     *
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("Confirm an order")
    public Result confirm(@RequestBody OrdersCancelDTO ordersCancelDTO) {
        orderService.confirm(ordersCancelDTO);
        return Result.success();
    }

    /**
     * Reject an order
     *
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("Reject an order")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }


    /**
     * Cancel an order
     *
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/cancel")
    @ApiOperation("Cancel an order")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * Deliver an order
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("Deliver an order")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    /**
     * Complete an order
     *
     * @param id
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("Complete an order")
    public Result complete(Long id) {
        orderService.complete(id);
        return Result.success();
    }

}
