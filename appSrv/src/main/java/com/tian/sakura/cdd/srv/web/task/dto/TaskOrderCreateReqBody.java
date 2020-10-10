package com.tian.sakura.cdd.srv.web.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 创建订单的请求报文
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class TaskOrderCreateReqBody {

    @ApiModelProperty("产品id")
    @NotBlank(message = "产品id不能为空")
    private String productId;
//    @ApiModelProperty("产品规格")
//    private String productSpecId;
    @ApiModelProperty("用户优惠券id")
    private String userCouponId;
    @ApiModelProperty("数量")
    @Min(value = 1, message = "最少选择一份")
    @NotNull(message = "数量不能为空")
    private Integer taskCount;
    @ApiModelProperty("任务金总金额")
    @NotNull(message = "任务金总金额不能为空")
    private BigDecimal taskTotalAmount;
    @ApiModelProperty("优惠金额")
    private BigDecimal couponAmount;
    @ApiModelProperty("拆豆总量,购买任务金赠送的拆豆，可空")
    private Integer beanCnt;
    @ApiModelProperty("实际支付金额")
    @NotNull(message = "实际支付金额不能为空")
    private BigDecimal payAmount;


}
