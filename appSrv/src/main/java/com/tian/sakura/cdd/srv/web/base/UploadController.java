package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.video.service.core.OSSCore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Api(value = "文件上传")
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private OSSCore OSSCore;

    @ApiOperation(value = "图片上传")
    @PostMapping("file")
    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> map = new HashMap<>();
        map.put("url", OSSCore.upload(file));
        return map;
    }
}
