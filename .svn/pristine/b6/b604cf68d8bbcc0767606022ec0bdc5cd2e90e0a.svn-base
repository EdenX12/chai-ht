package com.tian.sakura.cdd.db.dao.base;

import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.domain.base.FuncModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncModuleMapper extends AbstractSingleMapper<FuncModule, String> {

    List<FuncModule> selectAllPublished();

    int deleteFuncModule(@Param("id") String id);

    List<FuncModule> listFuncModule(FuncModule funcModule);
}