package com.moordash.controller.admin;


import com.moordash.dto.DishDTO;
import com.moordash.dto.DishPageQueryDTO;
import com.moordash.entity.Dish;
import com.moordash.result.PageResult;
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

/**
 * Dish management
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "Dish related interface")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Add a new dish
     *
     * @param dishDTO
     * @return
     */
    @PostMapping()
    @ApiOperation("Add a new dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Add a new dish：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        // clear cached data
        String key = "dish_" + dishDTO.getCategoryId();
        clearCache(key);

        return Result.success();
    }

    /**
     * Paginated query of dishes
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Paginated query of dishes")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Paginated query of dishes: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);//后绪步骤定义
        return Result.success(pageResult);
    }

    /**
     * Batch delete dishes
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Batch delete dishes")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Batch delete dishes: {}", ids);
        dishService.deleteBatch(ids);

        // Clear all cached dish data: all keys starting with dish_
        clearCache("dish_*");
        return Result.success();
    }

    /**
     * Query dish by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Query dish by id")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("Query dish by id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Update a dish
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update a dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("Update a dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);

        // Clear all cached dish data: all keys starting with dish_
        clearCache("dish_*");

        return Result.success();
    }

    /**
     * Query dishes by category id
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query dishes by category id")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * Start or stop the sale of a dish
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start or stop the sale of a dish")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);

        // Clear all cached dish data: all keys starting with dish_
        clearCache("dish_*");

        return Result.success();
    }

    /**
     * Clear cache
     *
     * @param pattern
     */
    private void clearCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
