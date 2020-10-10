package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class UpdateNickNameReqBody {

    @NotBlank(message = "昵称不能为空")
    @Pattern(regexp = ".{1,80}", message = "请输入长度不大于80的昵称")
    @ApiModelProperty("昵称")
    private String nickName;
}
