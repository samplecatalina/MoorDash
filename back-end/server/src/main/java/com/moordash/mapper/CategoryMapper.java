package com.moordash.mapper;

import com.github.pagehelper.Page;
import com.moordash.annotation.AutoFill;
import com.moordash.enumeration.OperationType;
import com.moordash.dto.CategoryPageQueryDTO;
import com.moordash.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * Insert data
     *
     * @param category
     */
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * Paginated query
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Delete category by id
     *
     * @param id
     */
    void deleteById(@Param("id") Long id);

    /**
     * Modify category by id
     *
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    /**
     * Query category by type
     *
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
