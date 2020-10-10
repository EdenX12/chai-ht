package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.req.func.AdminFuncModuleReq;
import com.tian.sakura.cdd.db.domain.base.FuncModule;
import com.tian.sakura.video.service.auth.FuncModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/func")
public class FuncModelController {

    @Autowired
    private FuncModuleService funcModuleService;

    @PostMapping("/listFuncModule")
    public ResultDto listFuncModule(@RequestBody AdminFuncModuleReq req) {
        return ResultDto.success().setObj(funcModuleService.listFuncModule(req));
    }

    @PostMapping("/insertFuncModule")
    public ResultDto insertFuncModule(@RequestBody FuncModule funcModule) {
        funcModuleService.insertFuncModule(funcModule);
        return ResultDto.success();
    }

    @PostMapping("/updateFuncModule")
    public ResultDto updateFuncModule(@RequestBody FuncModule funcModule) {
        funcModuleService.updateFuncModule(funcModule);
        return ResultDto.success();
    }

    @PostMapping("/deleteFuncModule")
    public ResultDto deleteFuncModule(@RequestBody FuncModule funcModule) {
        funcModuleService.deleteFuncModule(funcModule);
        return ResultDto.success();
    }

}
