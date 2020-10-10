package com.tian.sakura.cdd.srv.web.task.dto;

import io.swagger.annotations.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class TaskOrderPayReqBody {
    @ApiModelProperty("任务id")
    @NotBlank(message = "任务id不能为空")
    private String taskId;
    @ApiModelProperty("支付类型 0-微信;1-支付宝；2-账号")
    @Pattern(regexp = "[0|1|2]", message = "支付方式不在可选范围内")
    private String payType;

    private String ip;
}
