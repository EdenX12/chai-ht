package com.tian.sakura.cdd.batch.service;

import com.tian.sakura.cdd.db.domain.order.Order;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.manage.order.OrderManage;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.order.prd.PrdOrderPaymentLifeCycle;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.tian.sakura.cdd.common.constants.SrvConstants.PARAM_KEY_PRD_ORD_PAYTIME;

/**
 * 针对产品订单超时并且未支付的订单，执行关闭操作
 *
 * @author lvzonggang
 */

@Service
public class PrdOrderCloseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderManage orderManage;
    @Autowired
    private PrdOrderPaymentLifeCycle orderPaymentLifeCycle;
    @Autowired
    private ParamsManage paramsManage;

    public void closeOrder() {
        // 查询过期未付款的订单
        // 查询超过系统配置的未支付时间的订单，关闭订单。
        String payTimeMinutesValue = paramsManage.getValueByKey(PARAM_KEY_PRD_ORD_PAYTIME);
        Integer payTimeMinutes = Integer.valueOf(payTimeMinutesValue);

        DateTime now = DateTime.now();
        //最后支付时间 多计算了三分钟
        Date endPayTime = now.minusMinutes(payTimeMinutes + 3).toDate();

        List<Order> unPayOrderList = orderManage.selectUnPayOrder(endPayTime);
        logger.info("本次需要关闭的商品订单数量[{}]", unPayOrderList.size());

        for (Order order : unPayOrderList) {
            try {
                orderPaymentLifeCycle.closeOrder(order.getId());
            } catch (Exception e) {
                logger.error("商品订单[userTaskId={}]关闭异常", order.getId());
                logger.error(e.getMessage(),e);
            }

        }
    }

}
