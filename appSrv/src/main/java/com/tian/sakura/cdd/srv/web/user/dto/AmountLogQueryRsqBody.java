package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@Builder
@ApiModel
public class AmountLogQueryRsqBody {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("变化类型")
    private Integer changeType;
    @ApiModelProperty("变化类型对应的名称")
    private String changeName;
    @ApiModelProperty("变化金额")
    private BigDecimal changeAmount;

    @ApiModelProperty("变化时间")
    private Long changeTime;
    @ApiModelProperty("旧值")
    private BigDecimal oldAmount;
}
