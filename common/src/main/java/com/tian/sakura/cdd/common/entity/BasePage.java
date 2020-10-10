package com.tian.sakura.cdd.common.entity;

import lombok.Data;

@Data
public class BasePage {
    protected Integer pageNum = 1;

    protected Integer pageSize = 10;
}
