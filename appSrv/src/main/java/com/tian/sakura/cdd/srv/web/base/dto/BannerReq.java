package com.tian.sakura.cdd.srv.web.base.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;

@Data
@ApiModel

public class BannerReq {

    private RequestHead head;
    @Valid
    private BannerReqBody body;
}
