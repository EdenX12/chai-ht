package com.tian.sakura.cdd.srv.web.order.dto.shopcar;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 新增购物车请求报文
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class ShopCarEditReq {

    @Valid
    private RequestHead head;
    @Valid
    private ShopCarEditReqBody body;
}
