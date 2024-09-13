package com.moordash.mapper;

import com.github.pagehelper.Page;
import com.moordash.annotation.AutoFill;
import com.moordash.dto.DishPageQueryDTO;
import com.moordash.entity.Dish;
import com.moordash.enumeration.OperationType;
import com.moordash.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * Query the amount of dishes by category ID
     *
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * Add data to the dish table
     *
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * Paginated query of dishes
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Get dish by id
     *
     * @param id
     * @return
     */
    Dish getById(Long id);

    /**
     * Delete the dish data in the dish table by ID
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Update dish info
     *
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * Query dish by category ID
     *
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * Query dishes by setmeal ID
     *
     * @param id
     * @return
     */
    List<Dish> getBySetmealId(Long id);

    /**
     * Count the number of dishes according to the conditions
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
