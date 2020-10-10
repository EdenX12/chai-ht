package com.tian.sakura.cdd.controller;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.db.domain.area.Area;
import com.tian.sakura.video.service.auth.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/listArea")
    public ResultDto listArea(@RequestBody Area area) {
        return ResultDto.success().setObj(areaService.listArea(area));
    }
}
