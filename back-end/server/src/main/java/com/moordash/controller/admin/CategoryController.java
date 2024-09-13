package com.moordash.controller.admin;

import com.moordash.dto.CategoryDTO;
import com.moordash.dto.CategoryPageQueryDTO;
import com.moordash.entity.Category;
import com.moordash.result.PageResult;
import com.moordash.result.Result;
import com.moordash.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Category management
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "API related to category management")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * add a new category
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add a new category")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("Add a new category: {}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * Pagniated query of categories
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Pagniated query of categories")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("Pagniated query of categories: {}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete a category
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete a category")
    public Result<String> deleteById(Long id){
        log.info("Delete a category: {}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * Modify a category
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Modify a category")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * Activate/deactivate a category
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Activate/deactivate a category")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * Query category by type
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query category by type")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
