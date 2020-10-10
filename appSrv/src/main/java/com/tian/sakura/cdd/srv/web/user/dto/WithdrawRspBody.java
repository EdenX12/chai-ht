package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
@Builder
public class WithdrawRspBody {
    @ApiModelProperty("提现状态码")
    private Integer withdrawStatus;
    @ApiModelProperty("提现状态名称")
    private String withdrawStatusName;
    @ApiModelProperty("提现金额")
    private BigDecimal amount;
    @ApiModelProperty("申请时间")
    private long createTime;
}
