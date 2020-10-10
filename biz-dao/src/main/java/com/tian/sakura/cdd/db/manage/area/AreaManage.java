package com.tian.sakura.cdd.db.manage.area;

import com.tian.sakura.cdd.common.entity.AbstractSingleManage;
import com.tian.sakura.cdd.common.entity.AbstractSingleMapper;
import com.tian.sakura.cdd.db.dao.area.AreaMapper;
import com.tian.sakura.cdd.db.domain.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaManage extends AbstractSingleManage<Area,Integer> {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    protected AbstractSingleMapper<Area, Integer> getSingleMapper() {
        return areaMapper;
    }

    public List<Area> getParentList() {
        return areaMapper.getParentList();
    }

    public List<Area> getChildListById(Integer id) {
        return areaMapper.getChildListById(id);
    }

    public List<Area> findAll() {
        return areaMapper.findAll();
    }
}
