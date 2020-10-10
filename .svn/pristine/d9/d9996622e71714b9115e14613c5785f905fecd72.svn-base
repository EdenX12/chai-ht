package com.tian.sakura.cdd.srv.web.user.dto;

import com.tian.sakura.cdd.common.entity.BasePage;
import com.tian.sakura.cdd.srv.validator.annotation.CustEnumVal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class AmountLogQueryReqBody extends BasePage {

    @ApiModelProperty("收益类型 11-购买返现  12-拆家返佣  13-返任务金; 20-提现，21-支付")
    @CustEnumVal(value = "11,12,13,20,21", message = "收益类型参数错误")
    private Integer changeType;
    @ApiModelProperty("起始日期")
    private Date beginDate;
    @ApiModelProperty("截止日期")
    private Date endDate;
    @ApiModelProperty("收益方向 1-收入；2-支出;")
    @CustEnumVal(value = "1,2", message = "收益向类型参数错误")
    private Integer amtDirect;
}
