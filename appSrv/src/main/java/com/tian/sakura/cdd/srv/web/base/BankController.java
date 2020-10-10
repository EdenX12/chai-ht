package com.tian.sakura.cdd.srv.web.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.entity.KeyValuePair;
import com.tian.sakura.cdd.db.domain.base.Bank;
import com.tian.sakura.cdd.db.manage.base.BankManage;
import com.tian.sakura.cdd.srv.web.base.dto.BankQueryReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Api("基础数据-银行")
@RestController
@RequestMapping("bank")
public class BankController {

    @Autowired
    private BankManage bankManage;

    @PostMapping("list")
    @ApiOperation("查询银行数据列表，默认返回前20条数据")
    public List<KeyValuePair> list(@RequestBody BankQueryReq req) {
        // 默认返回20条记录
        PageHelper.startPage(1, 20);
        List<Bank> bankList = bankManage.findLikeBankName(req.getBody().getBankName());

        List<KeyValuePair> result = new ArrayList<>();

        bankList.forEach(item -> {
            result.add(new KeyValuePair(item.getBankName(), item.getId().toString()));
        });

        return result;
    }
}
