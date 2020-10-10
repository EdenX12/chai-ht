package com.tian.sakura.cdd.order.prd;

import com.tian.sakura.cdd.common.dict.EPayStatus;
import com.tian.sakura.cdd.order.context.BizBeanMap;
import com.tian.sakura.cdd.order.context.PrdOrderPayCallBackContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单支付生命周期变化服务
 *
 * <ul>
 * <li>创建-  支付状态-待支付  订单状态- 待支付；</li>
 * <li>取消 - 支付状态-不支付  订单状态- 取消；</li>
 * <li>逾期关闭 -  支付状态-不支付， 订单状态- 关闭</li>
 * <li>支付 -  支付状态-已支付， 订单状态- 待发货</li>
 * </ul>
 *
 * @author lvzonggang
 */

@Service
@Transactional
public class PrdOrderOptService {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 处理s_order, s_order_detail, s_order_product
     *
     * @param context
     */
    public void changePaymentLife(PrdOrderPayCallBackContext context) {
        //支付状态
        EPayStatus payStatus = context.getPayStatus();
        // 查询beanName
        String beanName = BizBeanMap.getPrdOrderStatusServiceBeanName(payStatus.getCode());
        logger.info("订单状态操作服务的beanName:" + beanName);
        PrdOrderStatusService orderStatusService = applicationContext.getBean(beanName, PrdOrderStatusService.class);
        orderStatusService.changePaymentLife(context);
    }


}
