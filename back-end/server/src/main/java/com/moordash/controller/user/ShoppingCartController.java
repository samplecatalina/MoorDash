package com.moordash.controller.user;

import com.moordash.dto.ShoppingCartDTO;
import com.moordash.entity.ShoppingCart;
import com.moordash.result.Result;
import com.moordash.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Shopping cart
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "Customer-side: shopping cart related interface")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Add to shopping cart
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("Add to shopping cart")
    public Result<String> add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("Add to shopping cart: {}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * View shopping cart
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("View shopping cart")
    public Result<List<ShoppingCart>> list(){
        return Result.success(shoppingCartService.showShoppingCart());
    }

    /**
     * Empty shopping cart
     *
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("Empty shopping cart")
    public Result<String> clean(){
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    /**
     * Delete a product from the shopping cart
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("Delete a product from the shopping cart")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("Deleting a product from the shopping cart: {}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }


}
