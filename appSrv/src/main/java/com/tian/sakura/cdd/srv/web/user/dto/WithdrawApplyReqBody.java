package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class WithdrawApplyReqBody {

    @ApiModelProperty("提现的银行卡id标识")
    @NotBlank(message = "提现银行卡标识不能为空")
    private String userBankId;
    @ApiModelProperty("提现金额")
    @Min(value = 0,message = "提现金额应大于零")
    private BigDecimal amount;

}
