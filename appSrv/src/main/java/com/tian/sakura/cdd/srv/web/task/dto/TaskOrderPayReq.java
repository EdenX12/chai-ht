package com.tian.sakura.cdd.srv.web.task.dto;

import com.tian.sakura.cdd.common.web.RequestHead;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@ApiModel
@Setter
@Getter
public class TaskOrderPayReq {

    //@Valid
    private RequestHead head;
    @Valid
    private TaskOrderPayReqBody body;
}
