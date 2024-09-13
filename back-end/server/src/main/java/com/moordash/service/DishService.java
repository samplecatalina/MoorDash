package com.moordash.service;

import com.moordash.dto.DishDTO;
import com.moordash.dto.DishPageQueryDTO;
import com.moordash.entity.Dish;
import com.moordash.result.PageResult;
import com.moordash.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * Add a new dish together with its flavor info
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * Paginated query of dishes
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Batch delete dishes
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get a dish by id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Update a dish together with its flavor info
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Query dishes by category id
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * Query dishes by flavor
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * Set the status of a dish to on sale or off sale
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
