package com.tian.sakura.cdd.srv.service.base;

import com.tian.sakura.cdd.db.domain.base.FuncModule;
import com.tian.sakura.cdd.db.manage.base.FuncModuleManage;
import com.tian.sakura.cdd.srv.web.base.dto.FuncModelRspBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能区查询方法
 *
 * @author lvzonggang
 */
@Service
public class FuncModelQueryService {

    @Autowired
    private FuncModuleManage funcModuleManage;

    public List<FuncModelRspBody> findAllPublished() {
        List<FuncModelRspBody> result = new ArrayList<>();
        List<FuncModule> dataList = funcModuleManage.selectAllPublished();

        for (FuncModule funcModule : dataList) {
            FuncModelRspBody body = FuncModelRspBody.builder()
                    .moduleName(funcModule.getModuleName())
                    .moduleIcon(funcModule.getModuleUrl())
                    .showOrder(funcModule.getsOrder())
                    .jumpUrl(funcModule.getJumpUrl())
                    .build();

            result.add(body);
        }
        return result;
    }
}
