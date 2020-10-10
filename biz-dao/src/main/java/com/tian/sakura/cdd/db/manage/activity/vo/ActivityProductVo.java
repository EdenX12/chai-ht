package com.tian.sakura.cdd.db.manage.activity.vo;

import java.math.BigDecimal;

import com.tian.sakura.cdd.common.entity.BasePage;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动对应的商品列表
 * @author liuhg
 *
 */
@Data
public class ActivityProductVo{
    private String productId;//商品id
    private String productImg;//商品图片
    private String productDes;//商品描述
    private BigDecimal productPrice;//商品活动促销价格
    private BigDecimal scribingPrice;//划线价格
    private BigDecimal totalReward;//商品总佣金
    private BigDecimal taskPrice;//任务金
    private BigDecimal successReward;//买家立返
    private BigDecimal everyReward;//躺赢金
    private int totalNumber;//已拆满任务线数量
    private int taskNumber;//一个商品的任务数
    private int receivedTask;//当前未满任务线的份额数
    private int userCount;//参与的拆家人数
    private int totalFocus;//关注人数
    private String startDate;//促销起始时间
    private String endDate;//促销结束时间

}
