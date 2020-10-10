package com.tian.sakura.cdd.common.req.user;

import com.tian.sakura.cdd.common.entity.BasePage;
import lombok.Data;

@Data
public class AdminUserLevel extends BasePage {

    private String id;

    private Integer levelType;

    private String levelName;

    private Integer minNumber;

    private Integer maxNumber;

    private Integer buyNumber;

    private Integer productNumber;
}
