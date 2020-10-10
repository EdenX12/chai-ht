package com.tian.sakura.cdd.srv.applistener.listener;

import com.tian.sakura.cdd.common.util.IdGenUtil;
import com.tian.sakura.cdd.db.domain.productReviewStat.ProductReviewStat;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.srv.applistener.event.ViewPrdDetailEvent;
import com.tian.sakura.cdd.srv.builder.ProductBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *  统计商品点击次数
 *
 * @author lvzonggang
 */
@Service
public class PrdDetailStatListener implements ApplicationListener<ViewPrdDetailEvent> {
    private transient Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductReviewStatManage productReviewStatManage;
    private Object object = new Object();

    @Override
    @Async("asyncEventExecutor")
    public void onApplicationEvent(ViewPrdDetailEvent event) {
        String productId = (String) event.getSource();
        //产品浏览次数加1
        synchronized (object) {
            ProductReviewStat productReviewStat = productReviewStatManage.selectByProductId(productId);
            if (productReviewStat != null) {
                productReviewStatManage.increase(productId);
            } else {
                productReviewStat = ProductBuilder.buildNewReviewStat(productId);
                productReviewStatManage.insert(productReviewStat);
            }
        }
        logger.info("浏览产品[product={}]详情，总浏览次数+1", productId);
    }
}
