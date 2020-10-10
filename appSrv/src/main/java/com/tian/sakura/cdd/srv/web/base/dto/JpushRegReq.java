package com.tian.sakura.cdd.srv.web.base.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 *
 *
 * @author lvzonggang
 */


@Setter
@Getter
@ApiModel
public class JpushRegReq {
    @Valid
    private RequestHead head;
    @Valid
    private JpushRegReqBody body;
}
