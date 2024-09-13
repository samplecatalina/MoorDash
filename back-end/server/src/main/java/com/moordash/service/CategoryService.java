package com.moordash.service;

import com.moordash.dto.CategoryDTO;
import com.moordash.dto.CategoryPageQueryDTO;
import com.moordash.entity.Category;
import com.moordash.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * Add a new category
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Paginated query
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Delete a category by id
     * @param id
     */
    void deleteById(Long id);

    /**
     * Update a category
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * Activate or deactivate a category
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query categories by type
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
