package com.tian.sakura.cdd.srv.service.activityProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.activity.Activity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.manage.activity.ActivityProductManage;
import com.tian.sakura.cdd.db.manage.activity.vo.ActivityProductQueryVo;
import com.tian.sakura.cdd.db.manage.activity.vo.ActivityProductVo;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import com.tian.sakura.cdd.srv.service.product.ProductCommissionService;
import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionParameter;
import com.tian.sakura.cdd.srv.service.task.TaskAwardService;
import com.tian.sakura.cdd.srv.service.task.TaskLineService;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityReq;
import com.tian.sakura.cdd.srv.web.base.dto.ActivityReqBody;
import com.tian.sakura.cdd.srv.web.base.dto.ProductRspBody;

/**
 * 根据活动代码查询对应的商品列表
 * 
 * @author liuhg
 *
 */
@Service
public class ActivityProductService {
	@Autowired
	private ProductCommissionService productCommissionService;
	@Autowired
	private ActivityProductManage activityProductManage;

	public PageInfo<ProductRspBody> getProductListByActityId(ActivityReq activityReq) {
		//转化为前段需要的分页数据
		PageInfo<ProductRspBody> result = new PageInfo<>();
		ActivityReqBody activityReqBody = activityReq.getBody();
		ActivityProductQueryVo queryVo = ActivityProductQueryVo.builder().activityId(activityReqBody.getId()).build();

		PageHelper.startPage(activityReqBody.getPageNum(), activityReqBody.getPageSize());

		List<ProductRspBody> l = new ArrayList<ProductRspBody>();
		if (activityReq.getBody().getType()==1) {// 活动

			List<ActivityProductVo> products = activityProductManage.getProductsByActivityId(queryVo);
			PageInfo<ActivityProductVo> pageInfo = PageInfo.of(products);


			BeanUtils.copyProperties(pageInfo, result);
			for (ActivityProductVo product : products) {
				ProductRspBody productRspBody = doBuildProductRspBody(product);
				l.add(productRspBody);
			}

			result.setList(l);
		}
		return result;
	}

	private ProductRspBody doBuildProductRspBody(ActivityProductVo product) {
		ProductRspBody productRspBody = new ProductRspBody();
		BeanUtils.copyProperties(product, productRspBody);
		DefaultPrdCommissionParameter commissionParameter = new DefaultPrdCommissionParameter(product.getProductId(),
				product.getTaskNumber(), product.getTotalReward());
		PrdCommissionCalculateResult calculateResult = productCommissionService
				.getProductCommission(commissionParameter);
		BeanUtils.copyProperties(calculateResult, productRspBody);

		return productRspBody;
	}

}
