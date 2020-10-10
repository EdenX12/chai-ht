package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.srv.web.base.dto.QRCodeReq;
import com.tian.sakura.video.service.core.QRCodeCore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("qrCode")
@Api(value = "二维码")
public class QRCodeController {

    @Autowired
    private QRCodeCore qrCodeCore;

    @ApiOperation(value = "获取二维码")
    @PostMapping("get")
    public Map<String, String> getQRCode(@RequestBody QRCodeReq qrCodeReq) {
        Map<String, String> map = new HashMap<>();
        map.put("qrCodeUrl", qrCodeCore.encode(qrCodeReq.getBody().getUrl()));
        return map;
    }
}
