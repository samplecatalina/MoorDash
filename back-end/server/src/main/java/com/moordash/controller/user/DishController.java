package com.moordash.controller.user;

import com.moordash.constant.StatusConstant;
import com.moordash.dto.DishDTO;
import com.moordash.entity.Dish;
import com.moordash.result.Result;
import com.moordash.service.DishService;
import com.moordash.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "Customer-side: dish view API")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Query dishes by category id
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query dishes by category id")
    public Result<List<DishVO>> list(Long categoryId) {

        // construct the key in redis, rule: dish_categoryId
        String key = "dish_" + categoryId;

        // check if the data exists in redis
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list != null && list.size() > 0) {
            // if exists, return directly, no need to query database
            return Result.success(list);
        }


        // if not exist, query from database and put into redis
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE); // query only dishes on sale

        list = dishService.listWithFlavor(dish);

        // put into redis
        redisTemplate.opsForValue().set(key, list);
        return Result.success(list);
    }


}
