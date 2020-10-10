package com.tian.sakura.cdd.srv.web.product;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.product.ProductTypeReq;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.product.ProductQueryService;
import com.tian.sakura.cdd.srv.service.task.ShareService;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.*;
import com.tian.sakura.cdd.wx.message.WxUser;
import com.tian.sakura.cdd.wx.service.WxAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
@Api(value = "商品相关")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductQueryService productQueryService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private ParamsService paramsService;
    @Autowired
    private WxAuthService wxAuthService;

//    @ApiOperation(value = "查询首页推荐商品数据")
//    @PostMapping("/getRecommendPrdList")
//    public List<RecommendPrdRspBody> findRecommendPrdList(@RequestBody ProductReq productReq) {
//        return productQueryService.findRecommendPrdList(productReq);
//    }

    @ApiOperation("查询全部产品分类- 最多展示2级")
    @GetMapping("findAllPrdType")
    public List<PrdTypeRspBody> findAllProductType() {
        return productQueryService.getAllProductType();
    }

    @ApiOperation(value = "查询首页- 精选热门商品数据")
    @PostMapping("/getHotPrdList")
    public RecommendPrdRspBody getHotPrdList(@RequestBody ProductReq productReq) {
        String recommendType = "1";
        productReq.getBody().setRecommendTypeId(recommendType);
        return productQueryService.getHotPrdList(productReq);
    }

    @ApiOperation(value = "查询首页- 优惠单品商品数据")
    @PostMapping("/getDiscountPrdList")
    public RecommendPrdRspBody getDiscountPrdList(@RequestBody ProductReq productReq) {
        String recommendType = "2";
        productReq.getBody().setRecommendTypeId(recommendType);
        return productQueryService.getDiscountPrdList(productReq);
    }


    @ApiOperation(value = "获取商品列表")
    @PostMapping("/getProductList")
    public  PageInfo<ProductRspBody> getProductList(@RequestBody ProductReq productReq) {

        return productQueryService.getProductList(productReq);

    }

    @ApiOperation(value = "获取商品详情")
    @PostMapping("/getProductDetail")
    public ProductDetailRspBody getProductDetail(@RequestBody @Valid ProductDetailQueryReq detailQueryReq) {
        String productId = detailQueryReq.getBody().getProductId();

        doTrackShare(detailQueryReq);

        return productQueryService.getProductDetail(productId);
    }

    private void doTrackShare(ProductDetailQueryReq detailQueryReq) {
        String code = detailQueryReq.getBody().getCode();
        if (StringUtils.isNotEmpty(code)) {
            try {
                String appid = (String)paramsService.getValue("gzh_app_id");
                String secret = (String)paramsService.getValue("gzh_app_secret");

                WxUser wxUser = wxAuthService.loginApp(appid,secret,code);
                logger.info("微信信息[{}]", JSON.toJSONString(wxUser));

                // 记录与分享者的关系
                shareService.trackShareRaletion(wxUser, detailQueryReq.getBody().getShareId());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    @ApiOperation(value = "首页，查询商品一级分类")
    @PostMapping("/getPrdType")
    public List<ProductTypeRspBody> getPrdType(@RequestBody ProductTypeReq productTypeReq) {
        if (StringUtils.isEmpty(productTypeReq.getBody().getProductTypeId())) {
            return productQueryService.findOneLevelType(productTypeReq);
        } else {
            return productQueryService.findTwoLevelType(productTypeReq.getBody().getProductTypeId());
        }
    }
}
