package com.tian.sakura.cdd.srv.web.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能区模块响应体
 *
 * @author lvzonggang
 */

@Setter
@Getter
@Builder
@ApiModel
public class FuncModelRspBody {
    @ApiModelProperty("功能名称")
    private String moduleName;
    @ApiModelProperty("顺序")
    private int showOrder;
    @ApiModelProperty("icon名称")
    private String moduleIcon;
    @ApiModelProperty("调整URL")
    private String jumpUrl;
    @ApiModelProperty("模块类型，扩展字段，比如跳转类，按钮类等等")
    private String moduleType;
}
