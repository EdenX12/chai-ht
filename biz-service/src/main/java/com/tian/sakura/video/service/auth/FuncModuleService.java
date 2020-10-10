package com.tian.sakura.video.service.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.common.req.func.AdminFuncModuleReq;
import com.tian.sakura.cdd.db.domain.base.FuncModule;
import com.tian.sakura.cdd.db.manage.base.FuncModuleManage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncModuleService {

    @Autowired
    private FuncModuleManage funcModuleManage;

    public void insertFuncModule(FuncModule funcModule) {
        funcModuleManage.insertSelective(funcModule);
    }

    public void updateFuncModule(FuncModule funcModule) {
        funcModuleManage.updateByPrimaryKeySelective(funcModule);
    }

    public void deleteFuncModule(FuncModule funcModule) {
        funcModuleManage.deleteFuncModule(funcModule.getId());
    }

    public PageInfo<FuncModule> listFuncModule(AdminFuncModuleReq req) {
        FuncModule funcModule = new FuncModule();
        BeanUtils.copyProperties(req, funcModule);
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return new PageInfo<>(funcModuleManage.listFuncModule(funcModule));
    }
}
