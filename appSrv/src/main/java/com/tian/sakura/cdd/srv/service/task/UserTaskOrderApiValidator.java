package com.tian.sakura.cdd.srv.service.task;

import com.tian.sakura.cdd.common.dict.*;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.domain.product.Product;
import com.tian.sakura.cdd.db.domain.task.TaskCoupon;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.domain.user.UserCoupon;
import com.tian.sakura.cdd.db.domain.user.UserTask;
import com.tian.sakura.cdd.db.domain.userTaskLine.UserTaskLine;
import com.tian.sakura.cdd.db.manage.product.ProductManage;
import com.tian.sakura.cdd.db.manage.task.TaskCouponManage;
import com.tian.sakura.cdd.db.manage.user.UserCouponManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskLineManage;
import com.tian.sakura.cdd.db.manage.user.UserTaskManage;
import com.tian.sakura.cdd.srv.builder.LoginBuilder;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderCreateReq;
import com.tian.sakura.cdd.srv.web.task.dto.TaskOrderPayReq;
import jdk.nashorn.internal.ir.ContinueNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class UserTaskOrderApiValidator {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
;
    @Autowired
    private UserTaskLineManage userTaskLineManage;
    @Autowired
    private ProductManage productManage;
    @Autowired
    private UserCouponManage userCouponManage;
    @Autowired
    private TaskCouponManage taskCouponManage;
    @Autowired
    private UserTaskManage userTaskManage;

    public void validateTaskOrderForCreate(SUser user, TaskOrderCreateReq req) {
        String userId = user.getId();
        String productId = req.getBody().getProductId();
        Integer taskCnt = req.getBody().getTaskCount();
        //查询商品
        Product product = productManage.selectByPrimary(productId);
        if (product == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "商品");
        }
        // 校验金额
        doCheckTotalAmt(product, req);

        // 校验优惠券
        doCheckCoupon(user, req);

        // 校验用户能否购买该产品的任务金
        doCheckTaskLimit(user, req);

    }

    public void validateTaskOrderForPay(SUser user, TaskOrderPayReq req) {
        // 校验userTaskId
        UserTask userTask = userTaskManage.selectByPrimary(req.getBody().getTaskId());

        if (userTask == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "任务金订单");
        }

        if (userTask.getPayStatus() != EPayStatus.TO_BE_PAY.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.STATUS_NOT_MATCH_OPERATE, "支付");
        }


    }

    private void doCheckTotalAmt(Product product, TaskOrderCreateReq req) {
        Integer taskCnt = req.getBody().getTaskCount();
        // 任务金
        BigDecimal taskPrice = product.getTaskPrice();
        BigDecimal totalAmt = taskPrice.multiply(new BigDecimal(taskCnt));
        logger.info("任务金[{}],购买数据[{}]，计算的总金额[{}]", taskPrice, taskCnt, totalAmt);
        if (req.getBody().getTaskTotalAmount().compareTo(totalAmt) != 0) {
            throw new BizRuntimeException(RespCodeEnum.ORDR_ITEM_NOT_EQUAL, "订单总金额");
        }
    }

    /**
     * 校验优惠券
     *
     * @param user
     * @param req
     */
    private void doCheckCoupon(SUser user, TaskOrderCreateReq req) {
        String couponId = req.getBody().getUserCouponId();
        if (StringUtils.isEmpty(couponId)) {
            return;
        }
        // 查询用户优惠券数据
        UserCoupon userCoupon = userCouponManage.selectByPrimary(couponId);
        // 数据是否存在
        if (userCoupon == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "用户优惠券");
        }
        // 是否为 任务金优惠券
        if (userCoupon.getCouponType() != ECouponType.TASK.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.COUPON_TYPE_NO_MATCH, "任务金");
        }
        // 券状态是否可用
        if (userCoupon.getCouponStatus() != ECouponStatus.TO_BE_USED.getCode()) {
            throw new BizRuntimeException(RespCodeEnum.COUPON_STATUS_NO_MATCH);
        }

        // 是否为该用户所有
        if (!StringUtils.equals(userCoupon.getUserId(), user.getId())) {
            throw new BizRuntimeException(RespCodeEnum.NOT_OPERATE_RECORED, "优惠券");
        }
        // 查询优惠券
        TaskCoupon taskCoupon = taskCouponManage.selectByPrimary(userCoupon.getCouponId());
        if (taskCoupon == null) {
            throw new BizRuntimeException(RespCodeEnum.CHECK_OBJECT_ERROR_TEMPLATE, "用户优惠券");
        }
        int useCon = taskCoupon.getUseCon();
        // 固定金额券
        if (useCon == ECouponUseCondition.FIX_AMT.getCode()) {
            // 优惠券金额
            BigDecimal coupon = taskCoupon.getCouponAmount();
            // 优惠券金额是否一致
            if (coupon.compareTo(req.getBody().getCouponAmount()) < 0) {
                throw new BizRuntimeException(RespCodeEnum.ORDR_ITEM_NOT_EQUAL, "优惠券金额");
            }

            // 实际支付金额是否一致
            BigDecimal payAmount = req.getBody().getTaskTotalAmount().subtract(coupon);

            if (payAmount.compareTo(BigDecimal.ZERO) == 1) {
                // do nothing.
            } else {
                payAmount = BigDecimal.ZERO;
            }
            if (payAmount.compareTo(req.getBody().getPayAmount()) != 0) {
                throw new BizRuntimeException(RespCodeEnum.ORDR_ITEM_NOT_EQUAL, "支付金额");
            }
        } else if (useCon == ECouponUseCondition.SUPER_COUPON.getCode()) {
            req.getBody().setPayAmount(BigDecimal.ZERO);
        }

    }

    private void doCheckTaskLimit(SUser user, TaskOrderCreateReq req) {
        String userId = user.getId();
        String productId = req.getBody().getProductId();
        Integer taskCnt = req.getBody().getTaskCount();

        Integer beanCnt = user.getRewardBean();
        beanCnt = beanCnt == null ? 0 : beanCnt;
        // 总限制数
        int totalLimit = LoginBuilder.instance().getTaskLimit(beanCnt, true);
        // 并行的产品限制
        int productCntLimit = LoginBuilder.instance().getTaskLimitProduct(beanCnt, true);
        //  一个产品可以并行的最大任务线数
        int productlimit = LoginBuilder.instance().getTaskLimitPerProduct(beanCnt, true);
        // 单个产品的任务线上限
        if (taskCnt > productlimit) {
            throw new BizRuntimeException(RespCodeEnum.TASK_LIMIT_PRODUCT);
        }


        // 查询该用户购买该产品的任务线数量
        List<UserTaskLine> userTaskLineList = userTaskLineManage.selectCntByUserIdGroupByPrdId(userId);
        Integer taskCntOfOwningProduct = 0;
        Integer taskCntOfOwningTotal = 0;
        Set<String> selectedPrdSet = new HashSet<>();
        for (UserTaskLine item : userTaskLineList) {
            boolean taskDoing = checkTaskDoing(item.getLineStatus());
            // 计算某类产品总的任务线数
            if (taskDoing && StringUtils.equals(item.getProductId(), productId)) {
                taskCntOfOwningProduct += item.getSumCount();
            }
            // 计算所有的任务数
            if (taskDoing) {
                taskCntOfOwningTotal += item.getSumCount();
                selectedPrdSet.add(item.getProductId());
            }
        }
        if (taskCntOfOwningProduct + taskCnt > productlimit) {
            throw new BizRuntimeException(RespCodeEnum.TASK_LIMIT_PRODUCT);
        }
        if (taskCntOfOwningTotal + taskCnt > totalLimit) {
            throw new BizRuntimeException(RespCodeEnum.TASK_LIMIT_PRODUCT);
        }

        if (!selectedPrdSet.contains(productId)
                && selectedPrdSet.size() + 1 > productCntLimit) {
            throw new BizRuntimeException(RespCodeEnum.TASK_LIMIT_PRODUCT);
        }
    }

    private boolean checkTaskDoing(int lineStatus) {
        return ETaskLineStatus.RECEIVE_TASK.getCode() == lineStatus
                || ETaskLineStatus.TRADING.getCode() == lineStatus
                || ETaskLineStatus.FEE_CALCULATING.getCode() == lineStatus
                || ETaskLineStatus.FEE_CALCULATED.getCode() == lineStatus;
    }
}
