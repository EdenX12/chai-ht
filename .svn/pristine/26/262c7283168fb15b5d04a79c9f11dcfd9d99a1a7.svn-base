package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ApiModel
public class ShopCarQueryRsqBody {
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("商品id")
    private String productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品图片")
    private String productImg;
    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty("商品规格id")
    private String productSpecId;
    @ApiModelProperty("商品规格")
    private String productSpecValueName;
    @ApiModelProperty("商品数量")
    private Integer count;

    @ApiModelProperty("任务金")
    private BigDecimal taskPrice;
    @ApiModelProperty("买家立返")
    private BigDecimal successReward;
    @ApiModelProperty("躺赢金")
    private BigDecimal everyReward;

    @ApiModelProperty("一个商品的任务数")
    private Integer taskNumber;
    @ApiModelProperty("当前未满任务线的份额数")
    private Integer receivedTask;
    @ApiModelProperty("参与的拆家人数")
    private Integer userCount;
    @ApiModelProperty("关注人数")
    private Integer totalFocus;

    private boolean focus;

}
