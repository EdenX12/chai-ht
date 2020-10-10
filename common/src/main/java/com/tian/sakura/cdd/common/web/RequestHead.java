package com.tian.sakura.cdd.common.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 请求头
 *
 * @author lvzonggang
 */

@Setter
@Getter
public class RequestHead {

    private Long requestTime;
    private String command;
    private String version;

    @NotNull(message = "channel渠道不能未空")
    private int channel;
}
