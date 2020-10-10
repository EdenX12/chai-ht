package com.tian.sakura.cdd.srv.web.product.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;

@Data
@ApiModel
public class ProductReq {
    @Valid
    private RequestHead head;
    @Valid
    private ProductReqBody body;
}
