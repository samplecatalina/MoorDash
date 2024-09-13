package com.moordash.controller.user;

import com.moordash.entity.Category;
import com.moordash.result.Result;
import com.moordash.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Api(tags = "Customer-side: Category API")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Query category
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query category")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
