package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.db.domain.express.Express;
import com.tian.sakura.video.service.auth.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @PostMapping("/listExpress")
    public ResultDto listExpress(@RequestBody Express express) {
        return ResultDto.success().setObj(expressService.listExpress(express));
    }

}
