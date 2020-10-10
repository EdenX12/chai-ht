package com.tian.sakura.cdd.srv.web.base.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 地区
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
@Builder
public class AreaRspBody {

    private int id;
    private String areaName;
    private int parentId;
    private List<AreaRspBody> children;
}
