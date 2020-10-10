package com.tian.sakura.cdd.common.req.product;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

@Data
public class RecommendProductReq extends BasePage {

    private String productId;

    private String productTypeId;

    private String recommendTypeId;

    private Integer isOnFace;

    private Integer sOrder;

    private Integer createUser;

    private Integer updateUser;

    private String productImg;

    private String productName;
}
