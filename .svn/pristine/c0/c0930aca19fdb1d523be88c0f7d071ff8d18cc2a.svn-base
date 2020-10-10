package com.tian.sakura.video.service.core;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
public class QRCodeCore {

    @Autowired
    private OSSCore OSSCore;

    public String encode(String content) {
        try {
            MultipartFile multipartFile = QRCodeUtil.encode(content);
            return OSSCore.upload(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizRuntimeException(RespCodeEnum.QRCODE_EXCEPTION);
        }
    }
}
