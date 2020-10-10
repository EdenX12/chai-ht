package com.tian.sakura.cdd.common.dict;

/**
 * 关注状态（0：未关注或取消关注 1：已关注）
 *
 * @author lvzonggang
 * @Date 2018/8/21
 */
public enum EFollowStatus {
    N(0),
    Y(1);

    EFollowStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

}
