package com.tian.sakura.cdd.db.manage.order.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@Setter
@Getter
@Builder
public class UserShopCarQueryVo {
    private String productId;
    private String productSpecId;
    private String userId;

}
