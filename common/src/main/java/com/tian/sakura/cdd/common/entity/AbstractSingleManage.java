package com.tian.sakura.cdd.common.entity;

import com.tian.sakura.cdd.common.dict.ERecordStatus;
import com.tian.sakura.cdd.common.util.IdGenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 基类
 *
 * @author lvzonggang
 */
public abstract class AbstractSingleManage<T extends BaseEntity, ID> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract AbstractSingleMapper<T, ID> getSingleMapper();

    public T selectByPrimary(ID id) {
        return getSingleMapper().selectByPrimaryKey(id);
    }

    public void insert(T record) {
        BaseEntity baseEntity = (BaseEntity)record;
        doBuildNewRecored(baseEntity);
    	getSingleMapper().insert(record);
    }

    public void doBuildNewRecored(BaseEntity baseEntity) {
        //数据库采用自增长模式
//        if (baseEntity.getId() == null){
//            baseEntity.setId((ID)IdGenUtil.generateId());
//        }
        //每个表的状态都不统一，在各自业务类实现赋值
        //baseEntity.setStatus(ERecordStatus.VALID.getCode());
        Date now = new Date();
        if(baseEntity.getCreateTime()==null){
        	baseEntity.setCreateTime(now);
        }
        baseEntity.setUpdateTime(now);
        //baseEntity.setCreateOper(UserHolderUtils.getUserNo());
        //baseEntity.setUpdateOper(UserHolderUtils.getUserNo());
    }

    public void insertSelective(T record){
        doBuildNewRecored(record);
        getSingleMapper().insertSelective(record);
    }

    public void doBuildRecoredForUpdate(BaseEntity baseEntity) {
        Date now = new Date();
        baseEntity.setUpdateTime(now);
        //baseEntity.setUpdateOper(UserHolderUtils.getUserNo());
    }

    public void updateByPrimaryKeySelective(T record) {
        BaseEntity baseEntity = (BaseEntity)record;
        doBuildRecoredForUpdate(baseEntity);
        //更新语句不能修改插入时间
        baseEntity.setCreateTime(null);
        baseEntity.setCreateOper(null);
        getSingleMapper().updateByPrimaryKeySelective(record);
    }

    public void updateByPrimaryKey(T record) {
        doBuildRecoredForUpdate(record);
        getSingleMapper().updateByPrimaryKey(record);
    }
    
    public void deleteByPrimaryKey(ID id){
    	getSingleMapper().deleteByPrimaryKey(id);
    }

    public void logicDeleteByPrimaryKey(T record){
        BaseEntity baseEntity = (BaseEntity)record;
        baseEntity.setStatus(ERecordStatus.INVALID.getCode());
        doBuildRecoredForUpdate(baseEntity);
        //更新语句不能修改插入时间
        baseEntity.setCreateTime(null);
        baseEntity.setCreateOper(null);
        getSingleMapper().updateByPrimaryKeySelective(record);

    }

    public int batchInsert(List<T> recordList) {
        for (T record : recordList) {
            BaseEntity baseEntity = (BaseEntity)record;
            doBuildNewRecored(baseEntity);
        }
        return getSingleMapper().batchInsert(recordList);
    }
}
