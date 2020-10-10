package com.tian.sakura.cdd.srv.web.base.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QRCodeReqBody {
    @NotBlank(message = "链接不能为空")
    private String url;
}
