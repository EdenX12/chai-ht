package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.product.*;
import com.tian.sakura.cdd.common.req.shop.ShopReq;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.product.ProductRecommend;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import com.tian.sakura.video.service.auth.ProductService;
import com.tian.sakura.video.service.auth.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;

    /**
     * 商品相关
     */
    @PostMapping("/listProduct")
    public ResultDto getProductList(@RequestBody AdminProductReq adminProductReq) {
        return ResultDto.success().setObj(productService.queryProductList(adminProductReq));
    }

    @PostMapping("/listUsableProduct")
    public ResultDto listUsableProduct(@RequestBody AdminProductReq adminProductReq) {
        return ResultDto.success().setObj(productService.listUsableProduct(adminProductReq));
    }

    @PostMapping("/listCategory")
    public ResultDto getProductTypeList(@RequestBody AdminProductReq adminProductReq) {
        return ResultDto.success().setObj(productService.getAllProductType());
    }

    @PostMapping("/insertProduct")
    public ResultDto addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResultDto.success();
    }

    @PostMapping("/updateProduct")
    public ResultDto updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResultDto.success();
    }

    @PostMapping("/deleteProduct")
    public ResultDto deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return ResultDto.success();
    }

    /**
     * 推荐商品相关
     */
    @PostMapping("/listRecommendProduct")
    public ResultDto listRecommend(@RequestBody RecommendProductReq req) {
        return ResultDto.success().setObj(productService.listRecommend(req));
    }

    @PostMapping("/insertRecommendProduct")
    public ResultDto insertRecommendProduct(@RequestBody ProductRecommend productRecommend) {
        productService.insertRecommendProduct(productRecommend);
        return ResultDto.success();
    }

    @PostMapping("/insertRecommendProductList")
    public ResultDto insertRecommendProductList(@RequestBody ProductRecommend productRecommend) {
        productService.insertRecommendProductList(productRecommend);
        return ResultDto.success();
    }

    @PostMapping("/updateRecommendProduct")
    public ResultDto updateRecommendProduct(@RequestBody ProductRecommend productRecommend) {
        productService.updateRecommendProduct(productRecommend);
        return ResultDto.success();
    }

    @PostMapping("/deleteRecommendProduct")
    public ResultDto deleteRecommendProduct(@RequestBody ProductRecommend productRecommend) {
        productService.deleteRecommendProduct(productRecommend);
        return ResultDto.success();
    }

    /**
     * 推荐分类相关
     */
    @PostMapping("/listRecommendType")
    public ResultDto listRecommendType(@RequestBody RecommendProductReq req) {
        return ResultDto.success().setObj(productService.listRecommendType());
    }

    @PostMapping("/listRecommendTypePage")
    public ResultDto listRecommendTypePage(@RequestBody RecommendTypeReq req) {
        return ResultDto.success().setObj(productService.listRecommendTypePage(req));
    }

    @PostMapping("/insertRecommendType")
    public ResultDto insertRecommendType(@RequestBody RecommendType recommendType) {
        productService.insertRecommendType(recommendType);
        return ResultDto.success();
    }

    @PostMapping("/updateRecommendType")
    public ResultDto updateRecommendType(@RequestBody RecommendType recommendType) {
        productService.updateRecommendType(recommendType);
        return ResultDto.success();
    }

    @PostMapping("/deleteRecommendType")
    public ResultDto deleteRecommendType(@RequestBody RecommendType recommendType) {
        productService.deleteRecommendType(recommendType);
        return ResultDto.success();
    }

    /**
     * 规格相关
     */
    @PostMapping("/listProductSpec")
    public ResultDto listProductSpec(@RequestBody ProductSpec productSpec) {
        return ResultDto.success().setObj(productService.listProductSpec(productSpec));
    }

    @PostMapping("/insertProductSpec")
    public ResultDto insertProductSpec(@RequestBody ProductSpec productSpec) {
        productService.insertProductSpec(productSpec);
        return ResultDto.success();
    }

    @PostMapping("/updateProductSpec")
    public ResultDto updateProductSpec(@RequestBody ProductSpec productSpec) {
        return productService.updateProductSpec(productSpec);
    }

    @PostMapping("/deleteProductSpec")
    public ResultDto deleteProductSpec(@RequestBody ProductSpec productSpec) {
        productService.deleteProductSpec(productSpec);
        return ResultDto.success();
    }

    /**
     * 规格值相关
     */
    @PostMapping("/listProductSpecValue")
    public ResultDto listProductSpecValue(@RequestBody ProductSpecValue productSpecValue) {
        return ResultDto.success().setObj(productService.listProductSpecValue(productSpecValue));
    }

    @PostMapping("/listProductSpecValuePage")
    public ResultDto listProductSpecValuePage(@RequestBody ProductSpecValueReq req) {
        return ResultDto.success().setObj(productService.listProductSpecValuePage(req));
    }

    @PostMapping("/insertProductSpecValue")
    public ResultDto insertProductSpecValue(@RequestBody ProductSpecValue productSpecValue) {
        productService.insertProductSpecValue(productSpecValue);
        return ResultDto.success();
    }

    @PostMapping("/updateProductSpecValue")
    public ResultDto updateProductSpecValue(@RequestBody ProductSpecValue productSpecValue) {
        productService.updateProductSpecValue(productSpecValue);
        return ResultDto.success();
    }

    @PostMapping("/deleteProductSpecValue")
    public ResultDto deleteProductSpecValue(@RequestBody ProductSpecValue productSpecValue) {
        productService.deleteProductSpecValue(productSpecValue);
        return ResultDto.success();
    }

    /**
     * 商品分类相关
     */
    @PostMapping("/listProductType")
    public ResultDto listProductType(@RequestBody AdminProductTypeReq req){
        return ResultDto.success().setObj(productService.listProductType(req));
    }

    @PostMapping("/listProductTypeChild")
    public ResultDto listProductTypeChild(@RequestBody AdminProductTypeReq req){
        return ResultDto.success().setObj(productService.listProductTypeChild(req));
    }

    @PostMapping("/insertProductType")
    public ResultDto insertProductType(@RequestBody ProductType productType){
        productService.insertProductType(productType);
        return ResultDto.success();
    }

    @PostMapping("/updateProductType")
    public ResultDto updateProductType(@RequestBody ProductType productType){
        productService.updateProductType(productType);
        return ResultDto.success();
    }

    @PostMapping("/deleteProductType")
    public ResultDto deleteProductType(@RequestBody ProductType productType){
        return productService.deleteProductType(productType);
    }

    @PostMapping("/listShop")
    public ResultDto listShop(@RequestBody ShopReq shopReq) {
        return ResultDto.success().setObj(shopService.listShop(shopReq));
    }
}
