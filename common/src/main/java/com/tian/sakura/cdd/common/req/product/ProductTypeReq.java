package com.tian.sakura.cdd.common.req.product;

import com.tian.sakura.cdd.common.web.RequestHead;
import lombok.Data;

@Data
public class ProductTypeReq {
    private RequestHead head;

    private ProductTypeReqBody body;
}
