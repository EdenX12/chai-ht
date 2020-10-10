package com.tian.sakura.cdd.db.manage.product.vo;

import com.tian.sakura.cdd.db.domain.product.Product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyTaskProductVo extends Product {
	private String taskLineId;
}
