package com.tian.sakura.cdd.db.domain.task;

import com.tian.sakura.cdd.db.domain.product.Product;

import lombok.Data;

@Data
public class MyTask extends Product{
	private String nickName;
	private String userName;
}
