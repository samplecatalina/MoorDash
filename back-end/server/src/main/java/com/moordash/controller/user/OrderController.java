package com.moordash.controller.user;

import com.moordash.dto.OrdersPaymentDTO;
import com.moordash.dto.OrdersSubmitDTO;
import com.moordash.result.PageResult;
import com.moordash.result.Result;
import com.moordash.service.OrderService;
import com.moordash.vo.OrderPaymentVO;
import com.moordash.vo.OrderSubmitVO;
import com.moordash.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * OrderController
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "Customer-side: Order API")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * User place an order
     *
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("User place an order")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("User place an order: {}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * Order payment
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("Order payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("Order payment: {}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("Generate pre-payment transaction order: {}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     * Query historical orders
     *
     * @param pageNum
     * @param pageSize
     * @param status 1 to be paid 2 wait for confirmation 3 confirmed
     *               4 in delivery 5 completed 6 cancelled
     * @return
     */
    @GetMapping("historyOrders")
    @ApiOperation("Query historical orders")
    public Result<PageResult> page(int pageNum, int pageSize, Integer status) {
        PageResult pageResult=orderService.pageQueryForUser(pageNum, pageSize, status);
        return Result.success(pageResult);
    }

    /**
     * Query order details
     *
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("Query order details")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * User cancel order
     *
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation("User cancel order")
    public Result cancel(@PathVariable Long id) throws Exception {
        orderService.userCancelById(id);
        return Result.success();
    }

    /**
     * One more order
     *
     * @param id
     * @return
     */
    @PostMapping("/repetition/{id}")
    @ApiOperation("One more order")
    public Result repetition(@PathVariable Long id) {
        orderService.repetition(id);
        return Result.success();
    }

    /**
     * Order reminder
     *
     * @param id
     * @return
     */
    @GetMapping("/reminder/{id}")
    @ApiOperation("Order reminder")
    public Result reminder(@PathVariable("id") Long id) {
        orderService.reminder(id);
        return Result.success();
    }
}
