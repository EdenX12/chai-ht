package com.tian.sakura.cdd.srv.service.task;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.params.ParamsService;

/**
 * 任务金相关计算
 * 计算买家立返佣金
 * 同组躺赢佣金
 * @author liuhg
 *
 */
@Service
public class TaskAwardService {
	@Autowired
	private ParamsService paramsService;

	// 根据商品总佣金计算买家立返佣金
	public BigDecimal buyerCommission(BigDecimal productTotalCommission) {
		Object obj = paramsService.getValue(GlobalConstants.BUYER_RATE);
		if (obj != null) {
			BigDecimal buyerRate = new BigDecimal(obj.toString());
			return productTotalCommission.multiply(buyerRate);
		} else {
			return null;
		}
	}


	// 根据商品总佣金以及任务线上任务个数计算同组躺赢佣金
	public BigDecimal sameGroupCommission(BigDecimal productTotalCommission, int taskNumber) {
		Object obj = paramsService.getValue(GlobalConstants.SAME_GROUP_RATE);
		if (obj != null) {
			BigDecimal sameGroupRate = new BigDecimal(obj.toString());
			return productTotalCommission.multiply(sameGroupRate)
					.divide(new BigDecimal(taskNumber),2,RoundingMode.HALF_UP)
					.setScale(2,RoundingMode.HALF_UP);
		} else {
			return null;
		}
	}

}
