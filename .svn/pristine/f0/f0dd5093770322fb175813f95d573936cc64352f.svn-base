package com.tian.sakura.cdd.srv.web.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.tian.sakura.cdd.srv.web.product.dto.PrdRspBody.PrdRspBodyBuilder;
import com.tian.sakura.cdd.srv.web.product.dto.PrdTypeRspBody.PrdTypeRspBodyBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Data
public class PrdShareRspBody{
    @ApiModelProperty("商品id")
    private String productId;
    @ApiModelProperty("商品图片")
    private String productImg;
    @ApiModelProperty("商品简介")
    private String productDesc;
    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty("任务金")
    private BigDecimal taskPrice;
    @ApiModelProperty("买家立返")
    private BigDecimal buyerCommission;
    @ApiModelProperty("躺赢金")
    private BigDecimal sameGroupCommission;
    @ApiModelProperty("已拆满任务线数量")
    private int totalNumber;
    @ApiModelProperty("一个商品的任务数")
    private int taskNumber;
    @ApiModelProperty("当前未满任务线的份额数")
    private int receivedTask;
    @ApiModelProperty("参与的拆家人数")
    private int userCount;
    @ApiModelProperty("关注人数")
    private int totalFocus;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像链接")
    private String userImg;
    @ApiModelProperty("分享标题")
    private String shareTitle; 
    @ApiModelProperty("二维码图片url")
    private String brCodeUrl;
}
