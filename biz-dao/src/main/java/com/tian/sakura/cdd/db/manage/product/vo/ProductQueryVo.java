package com.tian.sakura.cdd.db.manage.product.vo;

import com.tian.sakura.cdd.common.entity.BasePage;
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
public class ProductQueryVo extends BasePage {
    private String productTypeId;
    private String productType2Id;
    private String productName;
    private Integer sortType;
    private Integer sortDirect;
}
