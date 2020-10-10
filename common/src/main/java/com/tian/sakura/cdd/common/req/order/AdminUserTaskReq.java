package com.tian.sakura.cdd.common.req.order;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdminUserTaskReq extends BasePage {
    private String userId;

    private String productId;

    private Integer payStatus;

    private BigDecimal payAmount;

    private Date payTime;

    private String remark;

    private Integer taskNumber;

    private String orderSn;

    private String userCouponId;

    private Integer channel;

    private String nickName;

    private String productName;
}
