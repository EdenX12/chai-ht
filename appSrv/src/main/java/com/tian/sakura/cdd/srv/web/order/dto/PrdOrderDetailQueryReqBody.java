package com.tian.sakura.cdd.srv.web.order.dto;

import com.tian.sakura.cdd.common.annotation.NotAopLog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
public class PrdOrderDetailQueryReqBody {
    @ApiModelProperty("订单明细id")
    @NotBlank(message = "订单明细id不能未空")
    private String id;

}
