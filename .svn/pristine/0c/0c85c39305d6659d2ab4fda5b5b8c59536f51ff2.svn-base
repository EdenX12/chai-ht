package com.tian.sakura.cdd.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:数据导出，生成excel文件时的列名称集合
 * @author: chao
 * @time: 2018.6.11
 */
public class ColumnTitleMap {
    private Map<String, String> columnTitleMap = new HashMap<String, String>();
    private ArrayList<String> titleKeyList = new ArrayList<String> ();

    public ColumnTitleMap(Map<String,String> map, List<String>keyList) {
                initColu(map);
                initTitleKeyList(keyList);
        }
    /**
     * mysql用户表需要导出字段--显示名称对应集合
     */
    private void initColu(Map<String,String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " +entry.getValue() );
            columnTitleMap.put(entry.getKey(), entry.getValue());
        }

    }

    /**
     * mysql用户表需要导出字段集
     */
    private void initTitleKeyList(List<String> keyList) {
        for (int i = 0; i <keyList.size() ; i++) {
            titleKeyList.add(keyList.get(i));
        }


    }

    public Map<String, String> getColumnTitleMap() {
        return columnTitleMap;
    }

    public ArrayList<String> getTitleKeyList() {
        return titleKeyList;
    }
}

