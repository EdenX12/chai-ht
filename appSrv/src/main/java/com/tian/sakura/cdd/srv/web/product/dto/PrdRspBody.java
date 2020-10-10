package com.tian.sakura.cdd.srv.web.product.dto;

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
@Setter
@Getter
@ApiModel
@Builder
public class PrdRspBody {

    @ApiModelProperty("商品id")
    private String productId;
    @ApiModelProperty("商品图片")
    private String productImg;
    @ApiModelProperty("商品简介")
    private String productDesc;
    @ApiModelProperty("商品价格")
    private String productPrice;
    @ApiModelProperty("任务金")
    private String taskPrice;
    @ApiModelProperty("买家立返")
    private String successReward;
    @ApiModelProperty("躺赢金")
    private String everyReward;
    @ApiModelProperty("已拆满任务线数量")
    private String totalNumber;
    @ApiModelProperty("一个商品的任务数")
    private String taskNumber;
    @ApiModelProperty("当前未满任务线的份额数")
    private String receivedTask;
    @ApiModelProperty("参与的拆家人数")
    private String userCount;
    @ApiModelProperty("关注人数")
    private String totalFocus;
}
