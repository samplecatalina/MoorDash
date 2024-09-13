package com.moordash.vo;

import com.moordash.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishVO implements Serializable {

    private Long id;

    // dish name
    private String name;

    // dish category id
    private Long categoryId;

    // dish price
    private BigDecimal price;

    private String image;

    private String description;

    // 0: not on sale, 1: on sale
    private Integer status;

    private LocalDateTime updateTime;

    private String categoryName;

    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
