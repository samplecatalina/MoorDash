package com.moordash.mapper;

import com.moordash.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * Check if the current dish is associated with a set meal
     *
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * Batch insert and save the relationship between set meals and dishes
     *
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * Delete data in the set meal dish relationship table
     *
     * @param id
     */
    void deleteBySetmaleId(Long id);

    /**
     * Query dish information based on set meal information
     *
     * @param id
     * @return
     */
    List<SetmealDish> getBySetmealId(Long id);
}
