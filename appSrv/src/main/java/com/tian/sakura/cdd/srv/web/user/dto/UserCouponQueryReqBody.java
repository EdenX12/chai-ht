package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 请求体
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class UserCouponQueryReqBody {

    @ApiModelProperty("券类型 0-任务金 1-商铺券")
    // 扩展校验类型， 如果填写就校验
    private Integer couponType;
    @ApiModelProperty("券状态：0-未使用;1-已使用;2-过期")
    private Integer couponStatus;
    @ApiModelProperty("商品id")
    private String productId;
}
