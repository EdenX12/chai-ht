package com.tian.sakura.cdd.db.manage.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.base.FuncModuleMapper;
import com.tian.sakura.cdd.db.domain.base.FuncModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能区数据访问类
 *
 * @author lvzonggang
 */
@Service
@Transactional
public class FuncModuleManage extends AbstractSingleManage<FuncModule, String> {

    @Autowired
    private FuncModuleMapper funcModuleMapper;

    @Override
    protected AbstractSingleMapper getSingleMapper() {
        return funcModuleMapper;
    }

    public List<FuncModule> selectAllPublished() {
        return funcModuleMapper.selectAllPublished();
    }

    public void deleteFuncModule(String id) {
        funcModuleMapper.deleteFuncModule(id);
    }

    public List<FuncModule> listFuncModule(FuncModule funcModule) {
        return funcModuleMapper.listFuncModule(funcModule);
    }
}
