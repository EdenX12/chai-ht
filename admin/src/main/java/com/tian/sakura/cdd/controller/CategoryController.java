package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.product.CategoryReq;
import com.tian.sakura.video.service.auth.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping("/listCategory")
    public ResultDto listCategory(@RequestBody CategoryReq categoryReq){
        return  ResultDto.success().setObj(productTypeService.listCategory(categoryReq));
    }
}
