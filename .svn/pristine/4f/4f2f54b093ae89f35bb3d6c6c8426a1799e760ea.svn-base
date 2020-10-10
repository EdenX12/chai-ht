package com.tian.sakura.cdd.common.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 响应消息体
 *
 * @author lvzonggang
 */

@Setter
@Getter
public class ResponseMessage<T> {
    private ResponseHead head;
    private T body;

    public ResponseMessage() {

    }

    public ResponseMessage(ResponseHead head) {
        this(head, null);
    }

    public ResponseMessage(ResponseHead head, T body) {
        this.head = head;
        this.body = body;
    }

}
