package com.tian.sakura.cdd.common.dict;

/**
 *  用户等级 0 新手 1 见习拆家 2 初级拆家 3 中级拆家 4 高级拆家
 *
 * @author lvzonggang
 */
public enum EUserLevelType {

    SENIOR(4, 4, "高级拆家"),
    MIDDLE(3, 3, "中级拆家"),
    JUNIOR(2, 2, "初级拆家"),
    LEARN(1, 1, "见习拆家"),
    NEW_USER(0,5, "新手");


    EUserLevelType(Integer code,Integer id, String name){
        this.code = code;
        this.id = id;
        this.name = name;
    }

    private Integer code;
    private Integer id;
    private String name;

    public Integer getCode() {
        return code;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EUserLevelType getUserLevelTypeByCode(Integer code) {
        EUserLevelType[] values = EUserLevelType.values();
        for (EUserLevelType item : values) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
