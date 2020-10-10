package com.tian.sakura.cdd.common.req.product;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdminProductReq extends BasePage {
    private String id;
    private String shopId;
    private Integer typeId;
    private String productName;
    private String productImg;
    private String productDes;
    private Date createTime;
    private Integer createUser;
    private Date updateTime;
    private Integer updateUser;
    private Integer productStatus;
    private Integer taskNumber;
    private BigDecimal productPrice;
    private BigDecimal totalReward;
    private BigDecimal taskPrice;
    private String priceUnit;
    private String successReward;
    private String everyReward;
    private Integer productType;
    private String productDetail;
}
