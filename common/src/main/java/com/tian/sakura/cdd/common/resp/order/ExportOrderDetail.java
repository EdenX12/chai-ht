package com.tian.sakura.cdd.common.resp.order;

import lombok.Data;

import java.util.Date;

@Data
public class ExportOrderDetail {
    private String id;
    private String orderSn;
    private String addressName;
    private String addressPhone;
    private String addressDetail;
    private String shopName;
    private String productId;
    private String productName;
    private String productSpecValueName;
    private String price;
    private Integer productNumber;
    private String orderAmount;
    private Date createTime;
    private String shippingCode;
    private String deliverExplain;
}
