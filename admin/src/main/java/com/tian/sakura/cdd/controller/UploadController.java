package com.tian.sakura.cdd.controller;

import com.tian.sakura.video.service.core.OSSCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private OSSCore OSSCore;

    @PostMapping("file")
    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> map = new HashMap<>();
        map.put("url", OSSCore.upload(file));
        return map;
    }
}
