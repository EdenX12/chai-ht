package com.tian.sakura.cdd.srv.web.user.dto;

import com.tian.sakura.cdd.common.entity.BasePage;
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
@ApiModel
@Setter
@Getter
public class WithdrawQueryReqBody extends BasePage {
    @ApiModelProperty("提现申请起始时间 yyyy-MM-dd")
    private Date beginDate;
    @ApiModelProperty("提现申请截止时间 yyyy-MM-dd")
    private Date endDate;
    @ApiModelProperty("提现状态- 0 已申请 1 已打款 2 已驳回")
    private Integer withdrawStatus;
}
