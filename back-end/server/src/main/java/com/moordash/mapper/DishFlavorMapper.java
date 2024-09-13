package com.moordash.mapper;

import com.moordash.annotation.AutoFill;
import com.moordash.entity.DishFlavor;
import com.moordash.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    // Batch insert dish flavor data
    void insertBatch(List<DishFlavor> flavors);

    // Delete flavor data associated with the menu
    void deleteByDishId(Long id);

    // Query flavor data based on dish id
    List<DishFlavor> getByDishId(Long id);
}
