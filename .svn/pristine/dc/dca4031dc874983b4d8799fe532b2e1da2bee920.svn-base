package com.tian.sakura.cdd.console.web.demo;

import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("test")
    public Object test() {
        Map<String,String> result = new HashMap<>();

        result.put("rsp","app test successful!");
        return result;
    }

    @RequestMapping("fail")
    public Object fail() {
        throw new BizRuntimeException("99999999","测试异常处理");
    }
}
