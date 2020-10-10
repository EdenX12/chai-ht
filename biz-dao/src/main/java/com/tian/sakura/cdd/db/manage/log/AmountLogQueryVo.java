package com.tian.sakura.cdd.db.manage.log;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 *
 * @author lvzonggang
 */

@Setter
@Getter
public class AmountLogQueryVo extends BasePage {

    private String userId;
    private Integer changeType;
    private String changeTypeValues;
    private Date beginDate;
    private Date endDate;
    private Integer amtDirect;


}
