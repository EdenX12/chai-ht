package com.tian.sakura.cdd.srv.web.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 请求体
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class UserCouponRsqBody {

    @ApiModelProperty("用户券ID")
    private String id;
    @ApiModelProperty("券ID")
    private String couponId;
    @ApiModelProperty("券适用的产品id")
    private String productId;
    @ApiModelProperty("券状态：0-未使用;1-已使用;2-过期")
    private Integer couponStatus;
    @ApiModelProperty("券类型 0-任务金 1-商铺券")
    private Integer couponType;
    @ApiModelProperty("券名称")
    private String couponName;
    @ApiModelProperty("券面额")
    private BigDecimal couponAmount;
    @ApiModelProperty("券开始时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @ApiModelProperty("券截止时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    @ApiModelProperty("满减，最低消费金额")
    private BigDecimal minConsumeAmount;
    @ApiModelProperty("适用条件： 0-立减 1-满减")
    private Integer useCon;
}
