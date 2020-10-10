package com.tian.sakura.cdd.srv.service.params;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.srv.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.db.manage.params.ParamsManage;

import static com.tian.sakura.cdd.srv.GlobalConstants.*;

@Service
public class ParamsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final Map<String, Object> map = new ConcurrentHashMap<>();
	@Autowired
	private ParamsManage paramsManage;
   
	public Object getValue(String key) {
		Object obj = map.get(key);
		if(obj == null) {//从数据库读取
			String value = paramsManage.getValueByKey(key);
			if (value == null ||value.trim().equals("")) {
				logger.error("没有查到key="+key);
				return null;
			}else {
				map.put(key,value);
				obj = value;
			}
		}
		return obj;
	}

	public String getValueNoCache(String key) {
	    return paramsManage.getValueByKey(key);
    }

	public List<KeyValuePair> listShowableParams() {
		List<KeyValuePair> result = new ArrayList<>();
		//领取任务的奖励豆
		doAddParams(PARAM_KEY_ORDER_BEAN_CNT, result);
		// 支付商品订单奖励豆
		doAddParams(PARAM_KEY_PRODUCT_BEAN_CNT, result);
		// 返现比例
		doAddParams(BUYER_RATE, result);
		return result;
	}

	private void doAddParams(String key, List<KeyValuePair> result) {
		String beanCnt = paramsManage.getValueByKey(key);
		KeyValuePair item = new KeyValuePair(key, beanCnt);
		result.add(item);
	}

}
