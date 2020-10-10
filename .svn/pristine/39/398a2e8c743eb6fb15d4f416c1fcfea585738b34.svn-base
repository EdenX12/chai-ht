package com.tian.sakura.cdd.srv.web.base;

import com.alipay.api.domain.Account;
import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.srv.service.params.ParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Api("参数模块")
@RestController
@RequestMapping("params")
public class SystemParamController {

    @Autowired
    private ParamsService paramsService;

    @GetMapping("list")
    @ApiOperation("查询参数配置")
    public Map<String, String> listShowableParams() {
        List<KeyValuePair> result = paramsService.listShowableParams();

        return result.stream().collect(Collectors.toMap(KeyValuePair::getName, KeyValuePair::getValue));
    }
}
