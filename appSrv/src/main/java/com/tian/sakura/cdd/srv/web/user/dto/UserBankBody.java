package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class UserBankBody {
    @ApiModelProperty("id标识")
    private String userBankId;

    @ApiModelProperty("银行")
    @NotNull(message = "银行不能为空")
    private Integer bankId;

    private String bankCode;
    private String bankName;
    @ApiModelProperty("持卡人名称")
    @NotBlank(message = "持卡人名称不能为空")
    private String realName;
    @NotBlank(message = "身份证不能为空")
    @ApiModelProperty("身份证")
    private String idCard;
    @NotBlank(message = "手机不能为空")
    @ApiModelProperty("银行预留的手机")
    private String mobile;
    @NotBlank(message = "银行卡号不能为空")
    @ApiModelProperty("银行卡号")
    private String cardNum;
}
