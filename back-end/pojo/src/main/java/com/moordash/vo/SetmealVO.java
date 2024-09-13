package com.moordash.vo;

import com.moordash.entity.SetmealDish;
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
public class SetmealVO implements Serializable {

    private Long id;

    // setmeal category id
    private Long categoryId;

    // setmeal name
    private String name;

    // setmeal price
    private BigDecimal price;

    // status 0: not on sale 1: on sale
    private Integer status;

    private String description;

    private String image;

    private LocalDateTime updateTime;

    private String categoryName;

    // setmeal - dish relation
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}
