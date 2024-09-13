package com.moordash.mapper;

import com.github.pagehelper.Page;
import com.moordash.annotation.AutoFill;
import com.moordash.dto.SetmealPageQueryDTO;
import com.moordash.entity.Setmeal;
import com.moordash.enumeration.OperationType;
import com.moordash.vo.DishItemVO;
import com.moordash.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * Query amount of set meal by category id
     *
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * Insert data into set meal table
     *
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * Paginated query
     *
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Query set meal by id
     *
     * @param id
     * @return
     */
    Setmeal getById(Long id);

    /**
     * Delete set meal by id
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Update set meal table
     *
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * Conditional query
     *
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * Query dish options by id
     *
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemBySetmealId(Long id);

    /**
     * Count the amount of set meal by condition
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
