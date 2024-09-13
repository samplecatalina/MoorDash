package com.moordash.mapper;

import com.moordash.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * Conditional query shopping cart data
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * Update the amount of goods in the shopping cart according to the id
     *
     * @param shoppingCart
     */
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * Insert shopping cart data
     *
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);

    /**
     * Delete shopping cart data according to the user id
     *
     * @param currentId
     */
    void deleteByUserId(Long currentId);

    /**
     * Delete shopping cart product data on id
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Batch insert shopping cart objects into shopping cart
     *
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
