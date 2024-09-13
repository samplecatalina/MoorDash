package com.moordash.dto;

import com.moordash.entity.SetmealDish;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDTO implements Serializable {

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    // status: 0: deactivated 1: activated
    private Integer status;

    private String description;

    private String image;

    // setmeal - dishes relationship
    private List<SetmealDish> setmealDishes = new ArrayList<>();

}
