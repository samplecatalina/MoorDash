package com.moordash.controller.admin;


import com.moordash.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "Store related interface")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Set the business status of the store
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("Set the business status of the store")
    public Result setStatus(@PathVariable Integer status) {
        log.info("Set the business status of the store to be: {}",status == 1 ? "Open" : "Closed");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /**
     * Get the business status of the store
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("Get the business status of the store")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("Got the business status of the store: {}",status == 1 ? "Open" : "Closed");
        return Result.success(status);
    }

}
