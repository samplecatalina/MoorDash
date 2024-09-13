package com.moordash.controller.admin;

import com.moordash.result.Result;
import com.moordash.service.WorkspaceService;
import com.moordash.vo.BusinessDataVO;
import com.moordash.vo.DishOverViewVO;
import com.moordash.vo.OrderOverViewVO;
import com.moordash.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Work bench Controller
 */
@RestController
@RequestMapping("/admin/workspace")
@Slf4j
@Api(tags = "WorkSpace Controller related APIs")
public class WorkSpaceController {
    @Autowired
    private WorkspaceService workspaceService;

    /**
     * Query today's data on the workbench
     * @return
     */
    @GetMapping("/businessData")
    @ApiOperation("Query today's data on the workbench")
    public Result<BusinessDataVO> businessData(){

        // Get the start time of the day
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);

        // Get the end time of the day
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);
        return Result.success(businessDataVO);
    }

    /**
     * Query order management data
     * @return
     */
    @GetMapping("/overviewOrders")
    @ApiOperation("Query order management data")
    public Result<OrderOverViewVO> orderOverView(){
        return Result.success(workspaceService.getOrderOverView());
    }

    /**
     * Query dish overview
     * @return
     */
    @GetMapping("/overviewDishes")
    @ApiOperation("Query dish overview")
    public Result<DishOverViewVO> dishOverView(){
        return Result.success(workspaceService.getDishOverView());
    }

    /**
     * Query set meal overview
     * @return
     */
    @GetMapping("/overviewSetmeals")
    @ApiOperation("Query set meal overview")
    public Result<SetmealOverViewVO> setmealOverView(){
        return Result.success(workspaceService.getSetmealOverView());
    }
}
