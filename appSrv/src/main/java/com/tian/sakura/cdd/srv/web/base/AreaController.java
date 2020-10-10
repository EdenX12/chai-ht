package com.tian.sakura.cdd.srv.web.base;

import com.tian.sakura.cdd.common.annotation.NotAopLog;
import com.tian.sakura.cdd.db.domain.area.Area;
import com.tian.sakura.cdd.db.manage.area.AreaManage;
import com.tian.sakura.cdd.srv.web.base.dto.AreaRspBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明。
 *
 * @author lvzonggang
 */

@Api(value = "地区api" , tags = {"地区"})
@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaManage areaManage;

    @GetMapping("list")
    @ApiOperation("查询所有的地区")
    @NotAopLog
    public List<AreaRspBody> list() {
        List<AreaRspBody> result = new ArrayList<>();
        List<Area> areaList = areaManage.findAll();

        Map<Integer, AreaRspBody> provinceMap = new HashMap<>();
        Map<Integer, AreaRspBody> cityMap = new HashMap<>();

        List<AreaRspBody> countryAreaList = new ArrayList<>();
        for (Area area : areaList) {
            AreaRspBody body = AreaRspBody.builder()
                    .areaName(area.getAreaName())
                    .id(area.getId())
                    .parentId(area.getAreaParentId())
                    .build();
            if (area.getAreaDeep() == 1) {
                provinceMap.put(area.getId(), body);
            } else if (area.getAreaDeep() == 2) {
                cityMap.put(area.getId(), body);
            } else if (area.getAreaDeep() == 3) {
                countryAreaList.add(body);
            }
        }
        // 遍历三级地区，将三级地区挂在到二级地区上
        for (AreaRspBody area : countryAreaList) {
            mountChildren(cityMap, area);
        }
        // 遍历二级地区，将二级地区挂在到一级地区上
        cityMap.entrySet().stream().forEach(entry -> {
            AreaRspBody city = entry.getValue();
            mountChildren(provinceMap, city);
        });

        // 一级地区 遍历一级地区，输出结果
        provinceMap.entrySet().stream().forEach(entry -> {
            result.add(entry.getValue());
        });

        return result;
    }

    private void mountChildren(Map<Integer, AreaRspBody> parentMap, AreaRspBody candicate) {
        int parentId = candicate.getParentId();
        if (parentMap.containsKey(parentId)) {
            AreaRspBody provinceArea = parentMap.get(parentId);
            List<AreaRspBody> children = provinceArea.getChildren();
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(candicate);
            provinceArea.setChildren(children);
            parentMap.put(parentId, provinceArea);
        }
    }
}
