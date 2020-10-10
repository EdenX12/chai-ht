package com.tian.sakura.cdd.srv.web.task.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 创建订单的请求体
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class TaskOrderCreateReq {
    @Valid
    @NotNull(message = "请求头不能未空")
    private RequestHead head;
    @Valid
    @NotNull(message = "请求体不能未空")
    private TaskOrderCreateReqBody body;
}
