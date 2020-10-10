package com.tian.sakura.video.service.auth;

import com.tian.sakura.cdd.db.domain.area.Area;
import com.tian.sakura.cdd.db.manage.area.AreaManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaManage areaManage;

    public List<Area> listArea(Area area) {
        List<Area> areaList = areaManage.getParentList();
        areaList.forEach(a -> {
            a.setChildList(areaManage.getChildListById(a.getId()));
            a.getChildList().forEach(b->{
                b.setChildList(areaManage.getChildListById(b.getId()));
            });
        });
        return areaList;
    }
}
