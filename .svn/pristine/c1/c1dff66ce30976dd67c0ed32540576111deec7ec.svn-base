package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.srv.service.base.FuncModelQueryService;
import com.tian.sakura.cdd.srv.web.base.dto.FuncModelRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页功能区模块api
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("func")
@Api(value = "首页功能区api", tags = {"功能区"})
public class FuncModuleController {

    @Autowired
    private FuncModelQueryService funcModelQueryService;

    @RequestMapping(value = "listAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("查询首页功能区")
    public List<FuncModelRspBody> findAllPublished() {
        return funcModelQueryService.findAllPublished();
    }
}
