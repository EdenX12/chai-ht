package com.tian.sakura.cdd.srv.web.product.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 产品详情查询
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class ProductDetailQueryReq {

    private RequestHead head;
    @Valid
    private ProductDetailQueryReqBody body;
}
