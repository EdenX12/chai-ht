package com.tian.sakura.cdd.srv.web.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

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
@Builder
public class OrderPrdBody {
    @Tolerate
    public OrderPrdBody() {

    }

    @ApiModelProperty("商品id")
    @NotBlank(message = "产品id不能为空")
    private String productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品图片")
    private String productImg;
    @ApiModelProperty("商品简介")
    private String productDesc;
    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty("商品规格id, 多个规则，下划线_链接")
    @NotBlank(message = "商品规格id不能为空")
    private String productSpecId;
    @ApiModelProperty("商品规格")
    private String productSpecValueName;
    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer productNumber;
    @ApiModelProperty("物流配送方式 1线下自提，2商家送货，3快递包邮，4快递收费")
    private Integer deliveryType;

}
