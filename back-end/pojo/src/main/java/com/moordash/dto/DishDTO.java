package com.moordash.dto;

import com.moordash.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;

    private String name;

    private Long categoryId;

    private BigDecimal price;

    private String image;

    private String description;

    // 0: not on sale, 1: on sale
    private Integer status;

    private List<DishFlavor> flavors = new ArrayList<>();

}
