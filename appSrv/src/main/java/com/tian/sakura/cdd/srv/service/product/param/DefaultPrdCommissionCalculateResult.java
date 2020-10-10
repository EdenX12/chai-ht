package com.tian.sakura.cdd.srv.service.product.param;

import com.tian.sakura.cdd.srv.service.product.PrdCommissionCalculateResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品综合计算结果
 *
 * @author lvzonggang
 */

@Setter
@Getter
@Builder
public class DefaultPrdCommissionCalculateResult implements PrdCommissionCalculateResult {

    @ApiModelProperty("商品id")
    private String productId;
    @ApiModelProperty("买家立返")
    private BigDecimal successReward;
    @ApiModelProperty("躺赢金")
    private BigDecimal everyReward;
    @ApiModelProperty("已拆满任务线数量")
    private Integer totalNumber;
    @ApiModelProperty("紧邻已满任务线的当前未满任务线的份额数")
    private Integer receivedTask;
    @ApiModelProperty("参与的拆家人数")
    private Integer userCount;
    @ApiModelProperty("关注人数")
    private Integer totalFocus;
    @ApiModelProperty("当前未满任务线的份额数")
    private Integer currentReceivedTask;
    @ApiModelProperty("商品销量")
    private Integer sellQuantity;
	@ApiModelProperty("拆家已躺赢人次")
	private Integer hasWinCnt;


}
