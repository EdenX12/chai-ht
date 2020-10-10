package com.tian.sakura.cdd.db.dao.product;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.common.req.product.AdminProductReq;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.task.MyTask;
import com.tian.sakura.cdd.db.manage.mytask.vo.MyTaskQueryVo;
import com.tian.sakura.cdd.db.manage.product.vo.MyTaskProductVo;
import com.tian.sakura.cdd.db.manage.product.vo.ProductQueryVo;
import com.tian.sakura.cdd.db.manage.product.vo.ProductRecommendQueryVo;
import com.tian.sakura.cdd.db.manage.product.vo.ShopGroupProductVo;
import com.tian.sakura.cdd.db.manage.product.vo.ShopProductVo;
import com.tian.sakura.cdd.db.manage.product.vo.ShopRecommendPrd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends AbstractSingleMapper<Product, String> {

    List<Product> getProductList(ProductQueryVo queryVo);

    List<Product> selectRecommendPrdList(ProductRecommendQueryVo queryVo);

    List<Product> queryProductList(@Param("product") AdminProductReq product);

    List<Product> listUsableProduct(AdminProductReq adminProductReq);

    List<Product> getUserProducts(MyTaskQueryVo queryVo);

    //获取用户关注中的商品
    List<Product> getUserFollow(MyTaskQueryVo queryVo);
    //进行中的最新一条任务
    Product  myTasking(@Param("productId")String productId,@Param("userId")String userId);
    //结算中的最新一条任务
    MyTaskProductVo  myLatestSettleTaskLine(@Param("productId")String productId,@Param("userId")String userId);

    //进行中的战队任务
    List<MyTask>  teamTasking(String userId);
	//进行中的队长推荐
    public  List<MyTask> teamLeaderPrompting(MyTaskQueryVo queryVo);
    //结算中的任务
    List<MyTaskProductVo>  mySettleTask(MyTaskQueryVo queryVo);

    //店铺查询商品列表
    List<ShopProductVo> getShopProductList(@Param("shopId")String shopId,@Param("productStatus")Integer productStatus);

    //店铺商品总数量
    public Integer getShopProductCnt(@Param("shopId")Integer shopId);

    //店铺商品关注人数
    public Integer getShopProductFollwCnt(@Param("shopId")Integer shopId);

    //店铺正常商品
    public List<Product> getShopNormalProduct(ShopGroupProductVo pv);

    //查询组内商品
    public List<Product> getGroupProduct(ShopGroupProductVo pv);

    void updateTotalNumber(@Param("productLine") int productLine,@Param("productId") String productId);
    
    //店铺上架商品数量
    public int getShopProductOnCnt(String shopId);
    
    //店铺商品关注人数
    public Integer getShopProductBeFollowedCnt(Integer shopId);
    
    //店铺商品查询
    public List<Product> searchShopProduct(ShopGroupProductVo pv);

    //推荐商铺
    public List<ShopRecommendPrd> shopRecommendPrd(Integer shopId);

}