package com.tian.sakura.cdd.order.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */
public class BizBeanMap {

    private static Map<Integer, String> prdOrderStatusServiceBean = new HashMap<>();

    static {
        prdOrderStatusServiceBean.put(1,"prdOrderPayOptServcie");
        prdOrderStatusServiceBean.put(3,"prdOrderCancelOptService");
        prdOrderStatusServiceBean.put(4,"prdOrderCloseOptService");
    }

    public static String getPrdOrderStatusServiceBeanName(Integer payStatus) {
        return prdOrderStatusServiceBean.get(payStatus);
    }
}
