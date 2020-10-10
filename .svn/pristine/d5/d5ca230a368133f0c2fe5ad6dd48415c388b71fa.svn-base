package com.tian.sakura.cdd.srv.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.media.jfxmedia.logging.Logger;
import com.tian.sakura.cdd.common.dict.EFollowStatus;
import com.tian.sakura.cdd.common.dict.EProductStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.domain.productImg.ProductImg;
import com.tian.sakura.cdd.db.domain.shop.Shop;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserFollow;
import com.tian.sakura.cdd.db.manage.base.RecommendTypeManage;
import com.tian.sakura.cdd.db.manage.product.*;
import com.tian.sakura.cdd.db.manage.product.vo.ProductQueryVo;
import com.tian.sakura.cdd.db.manage.product.vo.ProductRecommendQueryVo;
import com.tian.sakura.cdd.db.manage.shop.ShopManage;
import com.tian.sakura.cdd.db.manage.user.UserFollowManage;
import com.tian.sakura.cdd.srv.applistener.event.ViewPrdDetailEvent;
import com.tian.sakura.cdd.srv.builder.ProductBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionParameter;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;
import com.tian.sakura.cdd.srv.web.product.dto.*;
import com.tian.sakura.cdd.common.req.product.ProductTypeReq;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 产品相关的查询服务
 *
 * @author lvzonggang
 */

@Service
public class ProductQueryService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductTypeManage productTypeManage;
    @Autowired
    private RecommendTypeManage recommendTypeManage;
    @Autowired
    private ProductManage productManage;
    @Autowired
    private ProductImgManage productImgManage;
    @Autowired
    private ProductSpecManage productSpecManage;
    @Autowired
    private ProductSpecValueManage productSpecValueManage;
    @Autowired
    private ProductCommissionService productCommissionService;
    @Autowired
    private UserFollowManage userFollowManage;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ShopManage shopManage;

    public List<ProductTypeRspBody> findOneLevelType(ProductTypeReq productTypeReq) {
        List<ProductType> productTypeList = productTypeManage.findOneLevelType();
        return toPrdTypeRsp(productTypeList);
    }

    public List<ProductTypeRspBody> findTwoLevelType(String productTypeId) {
        List<ProductType> productTypeList = productTypeManage.findTwoLevelType(productTypeId);

        return toPrdTypeRsp(productTypeList);
    }

    public List<PrdTypeRspBody> getAllProductType() {
        List<ProductType> productTypeList = productTypeManage.getAllProductType();

        List<PrdTypeRspBody> recommendTypeList = new ArrayList<>();
        //构建成树形结构
        //一级大类 key=id,value= 商品分类
        Map<String, PrdTypeRspBody> firstPrdTypeMap = new HashMap<>();
        List<PrdTypeRspBody> secondPrdTypeList = new ArrayList<>();
        for (ProductType productType : productTypeList) {
            PrdTypeRspBody prdTypeRspBody = PrdTypeRspBody.builder()
                    .typeName(productType.getTypeName())
                    .productdTypeId(productType.getId().toString())
                    .typeImg(productType.getTypeImg())
                    .showOrder(productType.getShowOrder())
                    .build();
            // 一级分类
            if (StringUtils.isEmpty(productType.getParentId())) {
                firstPrdTypeMap.put(productType.getId().toString(), prdTypeRspBody);
            }
            // 二级分类
            else if (productType.getLevel() == 2){
                prdTypeRspBody.setParentId(productType.getParentId());
                secondPrdTypeList.add(prdTypeRspBody);
            }

            //推荐类型
            if (productType.getFlag() == 1) {
                recommendTypeList.add(prdTypeRspBody);
            }
        }
        //将二级分类挂在到一级分类上
        for (PrdTypeRspBody prdTypeRspBody : secondPrdTypeList) {
            String parentId = prdTypeRspBody.getParentId();

            PrdTypeRspBody firstPrdType = firstPrdTypeMap.get(parentId);
            if (firstPrdType != null) {
                List<PrdTypeRspBody> children = firstPrdType.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                }
                children.add(prdTypeRspBody);
                firstPrdType.setChildren(children);
            } else {
                logger.warn("二级产品类型[id={},typeName={}]无上级分类！！",
                        prdTypeRspBody.getProductdTypeId(), prdTypeRspBody.getTypeName());
            }
        }

        //输出结果
        List<PrdTypeRspBody> oneWithChild = firstPrdTypeMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().getShowOrder()))
                .map(e -> e.getValue()).collect(Collectors.toList());

        List<PrdTypeRspBody> result = new ArrayList<>();
        // 构建推荐类型数据
        PrdTypeRspBody prdTypeRspBody = PrdTypeRspBody.builder()
                .typeName("推荐分类")
                .productdTypeId("-1")
                .typeImg("")
                .showOrder(0)
                .build();
        prdTypeRspBody.setChildren(recommendTypeList);
        result.add(prdTypeRspBody);
        result.addAll(oneWithChild);
        return result;
    }

    private List<ProductTypeRspBody> toPrdTypeRsp(List<ProductType> productTypeList ) {
        int i = 1;
        List<ProductTypeRspBody> result = new ArrayList<>();
        for (ProductType productType : productTypeList) {
            ProductTypeRspBody body = new ProductTypeRspBody();
            body.setProductTypeId(productType.getId().toString());
            body.setTypeName(productType.getTypeName());
            body.setShowOrder(i++);

            result.add(body);
        }
        return result;
    }

    public RecommendPrdRspBody getHotPrdList(ProductReq productReq) {
        //推荐类型
        ProductReqBody queryBody = productReq.getBody();
        ProductRecommendQueryVo queryVo = ProductRecommendQueryVo.builder()
                .productTypeId(queryBody.getTypeId())
                .recommendTypeId(queryBody.getRecommendTypeId())
                .build();
        //查询推荐类型
        RecommendType recommendType = recommendTypeManage.selectByPrimary(queryBody.getRecommendTypeId());

        List<Product> products = productManage.selectRecommendPrdList(queryVo);

        RecommendPrdRspBody result = new RecommendPrdRspBody();
        List<ProductRspBody> responseListItem = new ArrayList<>();
        for (Product product : products) {
            ProductRspBody prdRspBody = doBuildProductRspBody(product);
            responseListItem.add(prdRspBody);
        }
        result.setResponseListItem(responseListItem);
        result.setRecommendTitle( recommendType != null ? recommendType.getRecommendTitle() : "精选热门");
        return result;
    }

    public RecommendPrdRspBody getDiscountPrdList(ProductReq productReq) {
        //推荐类型
        ProductReqBody queryBody = productReq.getBody();
        ProductRecommendQueryVo queryVo = ProductRecommendQueryVo.builder()
                .productTypeId(queryBody.getTypeId())
                .recommendTypeId(queryBody.getRecommendTypeId())
                .build();
        //查询推荐类型
        RecommendType recommendType = recommendTypeManage.selectByPrimary(queryBody.getRecommendTypeId());
        logger.info("推荐类型查询[{}]完成", recommendType.getRecommendTitle());
        PageHelper.startPage(queryBody.getPageNum(),queryBody.getPageSize());
        List<Product> products = productManage.selectRecommendPrdList(queryVo);

        PageInfo<Product> pageInfo = PageInfo.of(products);
        logger.info("推荐类型下的商品查询[总数={},当前列表数={}]完成", pageInfo.getTotal(), products.size());
        //将pageinfo<Product> 转化成pageInfo<PrdRspBody>
        PageInfo<ProductRspBody> pageInfo2 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfo2);
        RecommendPrdRspBody result = new RecommendPrdRspBody();
        List<ProductRspBody> responseListItem = new ArrayList<>();
        for (Product product : products) {
            ProductRspBody productRspBody = doBuildProductRspBody(product);
            responseListItem.add(productRspBody);
        }
        pageInfo2.setList(responseListItem);

        result.setPageInfo(pageInfo2);
        result.setRecommendTitle( recommendType != null ? recommendType.getRecommendTitle() : "精选热门");
        return result;
    }

    private ProductRspBody doBuildProductRspBody(Product product) {
        ProductRspBody productRspBody = new ProductRspBody();
        BeanUtils.copyProperties(product, productRspBody);

        DefaultPrdCommissionParameter commissionParameter =
                new DefaultPrdCommissionParameter(product.getId(),product.getTaskNumber(), product.getTotalReward());
        PrdCommissionCalculateResult calculateResult =
                productCommissionService.getProductCommission(commissionParameter);
        BeanUtils.copyProperties(calculateResult, productRspBody);

        // 是否关注
        productRspBody.setFocus(doPrdFocus(product.getId()));
        return productRspBody;
    }

    public ProductDetailRspBody getProductDetail(String productId) {
        ProductDetailRspBody detailRspBody = new ProductDetailRspBody();
        //产品基础数据
        Product product = productManage.selectByPrimary(productId);
        if (product == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商品");
        }
        if (product.getProductStatus() != EProductStatus.PUBLISHED.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_PRD_STATUS, "查看详情");
        }

        Shop shop = shopManage.selectByPrimary(Integer.valueOf(product.getShopId()));
        detailRspBody.setShopName(shop != null ?shop.getShopName():"");
        //发布浏览商品时间
        applicationContext.publishEvent(new ViewPrdDetailEvent(productId));

        BeanUtils.copyProperties(product, detailRspBody);
        //图片列表
        List<ProductImg> productImgList = productImgManage.selectByProductId(productId);
        List<String> productImgUrlList = new ArrayList<>();
        for (ProductImg productImg : productImgList) {
            if (productImg.getImgType() == 2) {
                productImgUrlList.add(productImg.getImgUrl());
            }
        }
        detailRspBody.setProductImgList(productImgUrlList);

        //任务线相关数据
        DefaultPrdCommissionParameter commissionParameter =
                new DefaultPrdCommissionParameter(productId,product.getTaskNumber(), product.getTotalReward());
        PrdCommissionCalculateResult calculateResult =
                productCommissionService.getProductCommission(commissionParameter);
        BeanUtils.copyProperties(calculateResult, detailRspBody);

        //规格数据-产品productId
        List<ProductSpec> productSpecList = productSpecManage.listProductSpecByProductId(productId);
        List<ProductSpecValueVo> productSpecValueVos = new ArrayList<>();
        productSpecList.forEach(item -> {
                ProductSpecValueVo vo = new ProductSpecValueVo();
                BeanUtils.copyProperties(item, vo);
                vo.setSpecValueId(item.getProductSpecValueId());
                vo.setSpecValueName(item.getProductSpecValueName());
                vo.setSpecValueType(item.getProductSpecValueType());
                productSpecValueVos.add(vo);
        });
        detailRspBody.setSpecValueVoList(productSpecValueVos);

        //可选的规格- 类型下
        List<ProductSpecValue> specValueForUseList = productSpecValueManage.selectByProductTypeId(product.getType2Id());
        Map<String,List<ProductSpecValueVo>> specValueListVoList = ProductBuilder.extractSpecValueToMap(specValueForUseList);
        //detailRspBody.setTypeSpecValueList(specValueListVoList);
        detailRspBody.setTypeSpecValueMap(specValueListVoList);

        // 是否关注
        detailRspBody.setFocus(doPrdFocus(productId));

        return detailRspBody;
    }

    public PageInfo<ProductRspBody> getProductList(ProductReq req) {
        ProductQueryVo queryVo  = ProductQueryVo.builder()
                .productName(req.getBody().getProductName())
                .productTypeId(req.getBody().getTypeId())
                .productType2Id(req.getBody().getType2Id())
                .sortType(req.getBody().getSortType())
                .sortDirect(req.getBody().getSortDirect())
                .build();
        if (queryVo.getSortType() == null) {
            queryVo.setSortType(0);
            queryVo.setSortDirect(1);
        }
        queryVo.setPageNum(req.getBody().getPageNum());
        queryVo.setPageSize(req.getBody().getPageSize());

        PageHelper.startPage(req.getBody().getPageNum(), req.getBody().getPageSize());
        List<Product> products = productManage.getProductList(queryVo);
        PageInfo<Product> pageInfo = PageInfo.of(products);

        //将pageinfo<Product> 转化成pageInfo<PrdRspBody>
        PageInfo<ProductRspBody> pageInfo2 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfo2);
        List<ProductRspBody> responseListItem = new ArrayList<>();
        for (Product product : products) {
            ProductRspBody productRspBody = doBuildProductRspBody(product);
            responseListItem.add(productRspBody);
        }
        pageInfo2.setList(responseListItem);
        return pageInfo2;
    }

    private boolean doPrdFocus(String productId) {
        // 是否关注
        SUser user = LoginUserThreadLocal.getLoginUser();
        if (user != null) {
            // 查询是否关注
            UserFollow userFollow = userFollowManage.selectByUserIdAndProductId(user.getId(), productId);
            if (userFollow == null) {
                return false;
            } else {
                return userFollow.getFollowStatus() == EFollowStatus.Y.getCode();
            }
        }
        return false;
    }



}
