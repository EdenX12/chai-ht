package com.tian.sakura.video.service.auth;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.product.*;
import com.tian.sakura.cdd.common.resp.product.ProductResp;
import com.tian.sakura.cdd.common.resp.product.ProductTypeResp;
import com.tian.sakura.cdd.common.resp.product.RecommendProductResp;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.dao.product.ProductMapper;
import com.tian.sakura.cdd.db.dao.product.ProductSpecValueMapper;
import com.tian.sakura.cdd.db.dao.productImg.ProductImgMapper;
import com.tian.sakura.cdd.db.domain.base.RecommendType;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.product.ProductRecommend;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.product.ProductSpecValue;
import com.tian.sakura.cdd.db.domain.productImg.ProductImg;
import com.tian.sakura.cdd.db.domain.productType.ProductType;
import com.tian.sakura.cdd.db.domain.taskLine.TaskLine;
import com.tian.sakura.cdd.db.manage.base.RecommendTypeManage;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.product.ProductTypeManage;
import com.tian.sakura.cdd.db.manage.task.TaskLineManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductManage productManage;
    @Autowired
    private ProductTypeManage productTypeManage;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RecommendTypeManage recommendTypeManage;
    @Autowired
    private ProductSpecManage productSpecManage;
    @Autowired
    private ProductSpecValueMapper productSpecValueMapper;
    @Autowired
    private TaskLineManage taskLineManage;
    @Autowired
    private ProductImgMapper productImgMapper;

    public ProductResp getProductDetail(String productId) {
        return null;
    }

    public List<ProductType> getProductTypeList(ProductTypeReq productTypeReq) {
        return productTypeManage.findByTypeStatus(productTypeReq.getBody());
    }

    public PageInfo<Product> queryProductList(AdminProductReq adminProductReq) {
        PageHelper.startPage(adminProductReq.getPageNum(), adminProductReq.getPageSize());
        List<Product> products = productManage.queryProductList(adminProductReq);
        products.forEach(e -> {
            List<ProductImg> imgList = productImgMapper.queryProductImg(e.getId());
            e.setProductImgList(imgList == null ? new ArrayList<>() : imgList);
            if (imgList != null && imgList.size() >= 1) {
                e.setProductImg(imgList.get(0).getImgUrl());
                if (imgList.get(imgList.size() - 1).getImgType() == 0) {
                    e.setHomeProductImg(imgList.get(imgList.size() - 1).getImgUrl());
                    imgList.remove(imgList.get(imgList.size() - 1));
                }
                imgList.remove(imgList.get(0));
            }
            int[] typeIds = new int[2];
            if (StringUtils.isNoneEmpty(e.getTypeId())) {
                ProductType productType = productTypeManage.selectByPrimary(Integer.parseInt(e.getTypeId()));
                if (productType != null && StringUtils.isNotEmpty(productType.getParentId())) {
                    typeIds[0] = Integer.parseInt(productType.getParentId());
                    typeIds[1] = Integer.parseInt(e.getTypeId());
                }
            }
            e.setTypeId(JSON.toJSONString(typeIds));
        });
        return new PageInfo<>(products);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addProduct(Product product) {
        product.setId(IdGenUtil.uuid());
        product.setCreateTime(new Date());
        productMapper.insertSelective(product);
        //缩略图
        product.getProductImgList().forEach(e -> {
            e.setImgType(1);
            e.setProductId(product.getId());
            e.setShopId(Integer.parseInt(product.getShopId()));
            e.setCreateTime(new Date());
            productImgMapper.insertSelective(e);
        });
        //主图
        ProductImg productImg = new ProductImg();
        productImg.setImgUrl(product.getProductImg());
        productImg.setCreateTime(new Date());
        productImg.setImgType(2);
        productImg.setShopId(Integer.parseInt(product.getShopId()));
        productImg.setProductId(product.getId());
        productImgMapper.insertSelective(productImg);
        if (StringUtils.isNoneBlank(product.getHomeProductImg())) {
            //长方形图
            ProductImg homeProductImg = new ProductImg();
            homeProductImg.setImgUrl(product.getHomeProductImg());
            homeProductImg.setCreateTime(new Date());
            homeProductImg.setImgType(0);
            homeProductImg.setShopId(Integer.parseInt(product.getShopId()));
            homeProductImg.setProductId(product.getId());
            productImgMapper.insertSelective(homeProductImg);
        }
    }

    public List<ProductTypeResp> getAllProductType() {
        List<ProductType> productTypes = productTypeManage.getAllProductType();
        List<ProductTypeResp> respList = new ArrayList<>();
        productTypes.forEach(e -> {
            ProductTypeResp resp = new ProductTypeResp();
            resp.setId(Integer.valueOf(e.getId()));
            resp.setTypeName(e.getTypeName());
            respList.add(resp);
        });
        return respList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
        //删除所有图片重新添加
        productImgMapper.deleteByProduct(product.getId());
        //缩略图
        for (ProductImg e : product.getProductImgList()) {
            e.setImgType(1);
            e.setProductId(product.getId());
            e.setShopId(Integer.parseInt(product.getShopId()));
            e.setCreateTime(new Date());
            productImgMapper.insertSelective(e);
        }
        //主图
        ProductImg productImg = new ProductImg();
        productImg.setImgUrl(product.getProductImg());
        productImg.setCreateTime(new Date());
        productImg.setImgType(2);
        productImg.setShopId(Integer.parseInt(product.getShopId()));
        productImg.setProductId(product.getId());
        productImgMapper.insertSelective(productImg);
        if (StringUtils.isNoneBlank(product.getHomeProductImg())) {
            //长方形图
            ProductImg homeProductImg = new ProductImg();
            homeProductImg.setImgUrl(product.getHomeProductImg());
            homeProductImg.setCreateTime(new Date());
            homeProductImg.setImgType(0);
            homeProductImg.setShopId(Integer.parseInt(product.getShopId()));
            homeProductImg.setProductId(product.getId());
            productImgMapper.insertSelective(homeProductImg);
        }
    }

    public void deleteProduct(Product product) {
        productMapper.deleteByPrimaryKey(product.getId());
    }

    public List<Product> listUsableProduct(AdminProductReq adminProductReq) {
        return productManage.listUsableProduct(adminProductReq);
    }

    public PageInfo<RecommendProductResp> listRecommend(RecommendProductReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<RecommendProductResp> list = productManage.listRecommend(req);
        return new PageInfo<>(list);
    }

    public List<RecommendType> listRecommendType() {
        return recommendTypeManage.listRecommendType();
    }

    public PageInfo<RecommendType> listRecommendTypePage(RecommendTypeReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return new PageInfo<>(recommendTypeManage.listRecommendTypePage(req));
    }

    public void insertRecommendProduct(ProductRecommend productRecommend) {
        if (productManage.selectCountByProductId(productRecommend.getProductId(), productRecommend.getRecommendTypeId()) <= 0) {
            productManage.insertRecommendProduct(productRecommend);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertRecommendProductList(ProductRecommend productRecommend) {
        productRecommend.getProductList().forEach(e -> {
            ProductRecommend p = new ProductRecommend();
            p.setIsOnFace(1);
            p.setRecommendTypeId(productRecommend.getRecommendTypeId());
            p.setsOrder(1);
            p.setProductId(e.getId());
            p.setCreateTime(new Date());
            if (productManage.selectCountByProductId(e.getId(), productRecommend.getRecommendTypeId()) <= 0) {
                productManage.insertRecommendProduct(p);
            }
        });
    }

    public void updateRecommendProduct(ProductRecommend productRecommend) {
        productManage.updateRecommendProduct(productRecommend);
    }

    public void deleteRecommendProduct(ProductRecommend productRecommend) {
        productManage.deleteRecommendProduct(productRecommend.getId());
    }

    public void insertRecommendType(RecommendType recommendType) {
        recommendTypeManage.insertSelective(recommendType);
    }

    public void updateRecommendType(RecommendType recommendType) {
        recommendTypeManage.updateByPrimaryKeySelective(recommendType);
    }

    public void deleteRecommendType(RecommendType recommendType) {
        recommendTypeManage.deleteRecommendType(recommendType.getId());
    }

    public List<ProductSpec> listProductSpec(ProductSpec productSpec) {
        return productSpecManage.listProductSpec(productSpec);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertProductSpec(ProductSpec productSpec) {
        List<String> specIdList = Arrays.asList(JSON.parseObject(productSpec.getProductSpecValueId(), String[].class));
        List<ProductSpecValue> specValueList = productSpecValueMapper.selectByIds(specIdList);
        int count = 0;
        StringBuilder productSpecValueId = new StringBuilder();
        StringBuilder productSpecValueType = new StringBuilder();
        StringBuilder productSpecValueName = new StringBuilder();
        for (int i = 0; i < specIdList.size(); i++) {
            if (count == (specIdList.size() - 1)) {
                productSpecValueId.append(specValueList.get(i).getId());
                productSpecValueType.append(specValueList.get(i).getValueType());
                productSpecValueName.append(specValueList.get(i).getValueName());
            } else {
                productSpecValueId.append(specValueList.get(i).getId()).append("_");
                productSpecValueType.append(specValueList.get(i).getValueType()).append("_");
                productSpecValueName.append(specValueList.get(i).getValueName()).append("_");
            }
            count++;
        }
        productSpec.setProductSpecValueId(productSpecValueId.toString());
        productSpec.setProductSpecValueType(productSpecValueType.toString());
        productSpec.setProductSpecValueName(productSpecValueName.toString());
        //库存
        productSpec.setStockNumber(productSpec.getProductNumber());
        productSpecManage.insertSelective(productSpec);
        //增加任务线数据
        Product product = productManage.selectByPrimary(productSpec.getProductId());
        int productLine = taskLineManage.getLineNumberByPorduct(product.getId());
        //增加商品总任务数据
        productManage.updateTotalNumber(productSpec.getProductNumber(), product.getId());
        for (int i = productLine + 1; i <= productLine + productSpec.getProductNumber(); i++) {
            TaskLine taskLine = new TaskLine();
            taskLine.setProductId(product.getId());
            taskLine.setId(IdGenUtil.uuid());
            taskLine.setTotalTask(product.getTaskNumber());
            taskLine.setLineStatus(0);
            taskLine.setSettleStatus(0);
            taskLine.setLineOrder(i);
            taskLine.setCreateTime(new Date());
            taskLineManage.insertSelective(taskLine);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateProductSpec(ProductSpec productSpec) {
        List<String> specIdList = Arrays.asList(JSON.parseObject(productSpec.getProductSpecValueId(), String[].class));
        List<ProductSpecValue> specValueList = productSpecValueMapper.selectByIds(specIdList);
        int count = 0;
        StringBuilder productSpecValueId = new StringBuilder();
        StringBuilder productSpecValueType = new StringBuilder();
        StringBuilder productSpecValueName = new StringBuilder();
        for (int i = 0; i < specIdList.size(); i++) {
            if (count == (specIdList.size() - 1)) {
                productSpecValueId.append(specValueList.get(i).getId());
                productSpecValueType.append(specValueList.get(i).getValueType());
                productSpecValueName.append(specValueList.get(i).getValueName());
            } else {
                productSpecValueId.append(specValueList.get(i).getId()).append("_");
                productSpecValueType.append(specValueList.get(i).getValueType()).append("_");
                productSpecValueName.append(specValueList.get(i).getValueName()).append("_");
            }
            count++;
        }
        productSpec.setProductSpecValueId(productSpecValueId.toString());
        productSpec.setProductSpecValueType(productSpecValueType.toString());
        productSpec.setProductSpecValueName(productSpecValueName.toString());
        ProductSpec old = productSpecManage.selectByPrimary(productSpec.getId());
        if (productSpec.getProductNumber() < old.getProductNumber()) {
            return ResultDto.error("商品数量不得小于原商品数量");
        }
        if (productSpec.getProductNumber() > old.getProductNumber()) {
            productSpec.setStockNumber(productSpec.getStockNumber() + (productSpec.getProductNumber() - old.getProductNumber()));
            //增加任务线数据
            Product product = productManage.selectByPrimary(productSpec.getProductId());
            int productLine = taskLineManage.getLineNumberByPorduct(product.getId());
            for (int i = productLine + 1; i <= productLine + (productSpec.getProductNumber() - old.getProductNumber()); i++) {
                TaskLine taskLine = new TaskLine();
                taskLine.setProductId(product.getId());
                taskLine.setId(IdGenUtil.uuid());
                taskLine.setTotalTask(product.getTaskNumber());
                taskLine.setLineStatus(0);
                taskLine.setSettleStatus(0);
                taskLine.setLineOrder(i);
                taskLine.setCreateTime(new Date());
                taskLineManage.insertSelective(taskLine);
            }
            //追加商品表总任务数
            product.setTotalNumber(product.getTotalNumber() + (productSpec.getProductNumber() - old.getProductNumber()));
            product.setStockNumber(product.getStockNumber() + (productSpec.getProductNumber() - old.getProductNumber()));
            productManage.updateByPrimaryKeySelective(product);
        }
        productSpecManage.updateByPrimaryKeySelective(productSpec);
        return ResultDto.success();
    }

    public void deleteProductSpec(ProductSpec productSpec) {
        productSpecManage.deleteProductSpec(productSpec.getId());
    }

    public List<ProductSpecValue> listProductSpecValue(ProductSpecValue productSpecValue) {
        return productSpecValueMapper.listProductSpecValue(productSpecValue);
    }

    public PageInfo<ProductSpecValue> listProductSpecValuePage(ProductSpecValueReq req) {
        ProductSpecValue productSpecValue = new ProductSpecValue();
        BeanUtils.copyProperties(req, productSpecValue);
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return new PageInfo<>(productSpecValueMapper.listProductSpecValue(productSpecValue));
    }

    public void insertProductSpecValue(ProductSpecValue productSpecValue) {
        productSpecValueMapper.insertSelective(productSpecValue);
    }

    public void updateProductSpecValue(ProductSpecValue productSpecValue) {
        productSpecValueMapper.updateByPrimaryKeySelective(productSpecValue);
    }

    public void deleteProductSpecValue(ProductSpecValue productSpecValue) {
        productSpecValueMapper.deleteByPrimaryKey(productSpecValue.getId());
    }

    public PageInfo<ProductType> listProductType(AdminProductTypeReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return new PageInfo<>(productTypeManage.listProductType(req));
    }

    public void insertProductType(ProductType productType) {
        productTypeManage.insertSelective(productType);
    }

    public void updateProductType(ProductType productType) {
        productTypeManage.updateByPrimaryKeySelective(productType);
    }

    public PageInfo<ProductType> listProductTypeChild(AdminProductTypeReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return new PageInfo<>(productTypeManage.listProductTypeChild(req));
    }

    public ResultDto deleteProductType(ProductType productType) {
        ProductType p = productTypeManage.selectByPrimary(productType.getId());
        if (p == null) {
            return ResultDto.error("分类不存在");
        }
        if (StringUtils.isEmpty(p.getParentId())) {
            List<ProductType> childList = productTypeManage.queryProductTypeByParentId(p.getId());
            if (childList.size() > 0) {
                return ResultDto.error("该分类下存在分类，不能删除");
            }
        }
        productTypeManage.deleteByPrimaryKey(productType.getId());
        return ResultDto.success();
    }
}
