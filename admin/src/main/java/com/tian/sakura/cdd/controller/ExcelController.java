package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.security.UserUtils;
import com.tian.sakura.video.service.auth.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExportExcelService exportExcelService;

    @PostMapping("/exportOrderForShipped")
    public void exportOrderForShipped(HttpServletResponse response, HttpServletRequest request) throws IOException {
        exportExcelService.exportOrderForShipped(response, request, UserUtils.getCurrentUser().getShopId());
    }

    @PostMapping("/exportUserWithdraw")
    public void exportUserWithdraw(HttpServletResponse response, HttpServletRequest request) throws IOException {
        exportExcelService.exportUserWithdraw(response, request);
    }

    @PostMapping("/uploadOrderShipped")
    public ResultDto uploadOrderShipped(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResultDto.error("上传文件不能为空");
        }
        exportExcelService.uploadOrderShipped(file);
        return ResultDto.success();
    }
}