package com.tian.sakura.cdd.srv.service.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.dict.EFollowStatus;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.order.UserShopCar;
import com.tian.sakura.cdd.db.domain.product.ProductSpec;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserFollow;
import com.tian.sakura.cdd.db.manage.order.UserShopCarManage;
import com.tian.sakura.cdd.db.manage.product.ProductSpecManage;
import com.tian.sakura.cdd.db.manage.user.UserFollowManage;
import com.tian.sakura.cdd.srv.builder.ShopCarBuilder;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import com.tian.sakura.cdd.srv.service.product.ProductCommissionService;
import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionParameter;
import com.tian.sakura.cdd.srv.web.order.dto.shopcar.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class ShopCarApiService {
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductCommissionService productCommissionService;

    @Autowired
    private UserShopCarManage shopCarManage;
    @Autowired
    private ProductSpecManage productSpecManage;

    @Autowired
    private UserFollowManage userFollowManage;

    public PageInfo<ShopCarQueryRsqBody> list(String userId, ShopCarQueryReqBody body) {
        PageHelper.startPage(body.getPageNum(), body.getPageSize());
        List<UserShopCar> userShopCarList = shopCarManage.selectByUserId(userId);

        PageInfo<UserShopCar> pageInfo = PageInfo.of(userShopCarList);

        PageInfo<ShopCarQueryRsqBody> result = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, result);

        List<ShopCarQueryRsqBody> bodyList = new ArrayList<>();
        for (UserShopCar shopCar : userShopCarList) {

            ShopCarQueryRsqBody rspBody = new ShopCarQueryRsqBody();
            rspBody.setShopName(shopCar.getShopName());
            rspBody.setProductName(shopCar.getProductName());
            rspBody.setProductImg(shopCar.getProductImg());
            rspBody.setProductId(shopCar.getProductId());
            rspBody.setProductSpecId(shopCar.getProductSpecId());
            rspBody.setProductSpecValueName(shopCar.getProductSpecValueName());

            rspBody.setCount(shopCar.getCount());
            rspBody.setProductPrice(shopCar.getPrice());
            rspBody.setTaskNumber(shopCar.getTaskNumber());
            rspBody.setTaskPrice(shopCar.getTaskPrice());

            DefaultPrdCommissionParameter commissionParameter =
                    new DefaultPrdCommissionParameter(shopCar.getProductId(),
                            shopCar.getTaskNumber(), shopCar.getTotalReward());
            PrdCommissionCalculateResult calculateResult =
                    productCommissionService.getProductCommission(commissionParameter);
            BeanUtils.copyProperties(calculateResult, rspBody);

            // 是否关注
            rspBody.setFocus(doPrdFocus(rspBody.getProductId()));


            bodyList.add(rspBody);
        }

        result.setList(bodyList);
        return result;
    }


    public List<ShopCarQueryRsqBodyNoPage> listNoPage(String userId) {
        List<UserShopCar> userShopCarList = shopCarManage.selectByUserId(userId);
        Map<String, List<ShopCarQueryRsqBody>> shopPrdMap = new HashMap<>();
        for (UserShopCar userShopCar : userShopCarList) {
            String shopId = userShopCar.getShopId();
            List<ShopCarQueryRsqBody> shopPrdList = null;
            if (shopPrdMap.containsKey(shopId)) {
                shopPrdList = shopPrdMap.get(shopId);
                shopPrdList.add(copyShopPrd(userShopCar));
            } else {
                shopPrdList = new ArrayList<>();
                shopPrdList.add(copyShopPrd(userShopCar));
            }
            shopPrdMap.put(shopId, shopPrdList);
        }

        List<ShopCarQueryRsqBodyNoPage> result = new ArrayList<>();
        for (Map.Entry<String, List<ShopCarQueryRsqBody>> entry : shopPrdMap.entrySet()) {
            ShopCarQueryRsqBodyNoPage bodyNoPage = new ShopCarQueryRsqBodyNoPage();
            bodyNoPage.setShopId(entry.getKey());

            List<ShopCarQueryRsqBody> shopPrdList = entry.getValue();
            bodyNoPage.setShopName(shopPrdList.get(0).getShopName());
            bodyNoPage.setShopPrdList(shopPrdList);

            result.add(bodyNoPage);
        }
        return result;
    }

    private ShopCarQueryRsqBody copyShopPrd(UserShopCar shopCar) {
        ShopCarQueryRsqBody rspBody = new ShopCarQueryRsqBody();
        rspBody.setShopName(shopCar.getShopName());
        rspBody.setProductName(shopCar.getProductName());
        rspBody.setProductImg(shopCar.getProductImg());
        rspBody.setProductId(shopCar.getProductId());
        rspBody.setProductSpecId(shopCar.getProductSpecId());
        rspBody.setProductSpecValueName(shopCar.getProductSpecValueName());

        rspBody.setCount(shopCar.getCount());
        rspBody.setProductPrice(shopCar.getPrice());
        rspBody.setTaskNumber(shopCar.getTaskNumber());
        rspBody.setTaskPrice(shopCar.getTaskPrice());

        DefaultPrdCommissionParameter commissionParameter =
                new DefaultPrdCommissionParameter(shopCar.getProductId(),
                        shopCar.getTaskNumber(), shopCar.getTotalReward());
        PrdCommissionCalculateResult calculateResult =
                productCommissionService.getProductCommission(commissionParameter);
        BeanUtils.copyProperties(calculateResult, rspBody);

        // 是否关注
        rspBody.setFocus(doPrdFocus(rspBody.getProductId()));

        return rspBody;
    }

    public void addShopCar(SUser user, ShopCarAddReq req) {
        String productId = req.getBody().getProductId();
        String productSpecId = req.getBody().getProductSpecId();
        String userId = user.getId();
        // 校验商品是否存在

        // 校验规格是否有效
        ProductSpec productSpec = productSpecManage.selectByProductIdAndSpecValueIds(productId, productSpecId);
        if (productSpec == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商品规格");
        }

       UserShopCar dbShopCar = shopCarManage.selectByLogicId(userId, productId, productSpecId);
       if (dbShopCar == null) {
           dbShopCar = ShopCarBuilder.build(req.getBody());
           dbShopCar.setUserId(userId);
           dbShopCar.setProductSpecId(productSpec.getId());
           dbShopCar.setPrice(productSpec.getProductPrice());
           dbShopCar.setCheckStatus(1);
           shopCarManage.insert(dbShopCar);
       } else {
           // 累计数量
           Integer totalCount = dbShopCar.getCount() + req.getBody().getCount();
           UserShopCar updShopCar = new UserShopCar();
           updShopCar.setId(dbShopCar.getId());
           updShopCar.setCount(totalCount);
           updShopCar.setCheckStatus(1);
           shopCarManage.updateByPrimaryKeySelective(updShopCar);
       }

    }

    public void editShopCar(SUser user, ShopCarEditReq req) {
        List<ShopCarEditBody> shopcarList = req.getBody().getShopCarList();

        if (shopcarList != null && !shopcarList.isEmpty()) {
            for (ShopCarEditBody body : shopcarList) {
                if (body.getCount() == null && body.getCheckStatus() == null) {
                    continue;
                }
                UserShopCar dbShopCar = shopCarManage.selectByPrimary(body.getShopCarId());
                if (dbShopCar == null) {
                    throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "购物车商品");
                }
                if (!StringUtils.equals(user.getId(), dbShopCar.getUserId())) {
                    throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
                }

                UserShopCar updShopCar = new UserShopCar();
                updShopCar.setId(body.getShopCarId());
                if (body.getCheckStatus() != null) {
                    updShopCar.setCheckStatus(body.getCheckStatus());
                }

                if (body.getCount() != null && body.getCount() >=1) {
                    updShopCar.setCount(body.getCount());
                }

                shopCarManage.updateByPrimaryKeySelective(updShopCar);
            }
        }
    }

    public void batchDelete(SUser user, List<String> ids) {
        for (String id : ids) {
            deleteById(user, id);
        }
    }

    public void deleteById(SUser user, String id) {
        UserShopCar dbShopCar = shopCarManage.selectByPrimary(id);
        if (dbShopCar == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "购物车商品");
        }
        if (!StringUtils.equals(user.getId(), dbShopCar.getUserId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_QUERY_RECORED);
        }

        shopCarManage.deleteByPrimaryKey(id);
    }

    public int findCount(SUser user) {
        return shopCarManage.selectCntByUserId(user.getId());
    }


    private boolean doPrdFocus(String productId) {
        // 是否关注
        SUser user = LoginUserThreadLocal.getLoginUser();
        return userFollowManage.checkFollow(user, productId);
    }
}
