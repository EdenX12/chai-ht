package com.tian.sakura.cdd.db.manage.shop.vo;

import java.util.Date;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

@Data
public class ShopCouponQryVo extends BasePage{
	private String shopId;
	private Integer couponState;
	private Date qryDate;
}
