package com.tian.sakura.cdd.common.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Setter
@Getter
@ToString
public class ResponseHead {
    private String respCode;
    private String respMsg;
    private int status;

}
