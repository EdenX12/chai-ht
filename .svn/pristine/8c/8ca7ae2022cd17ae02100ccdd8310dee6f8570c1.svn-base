package com.tian.sakura.cdd.srv.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 签到响应体报文
 *
 * @author lvzonggang
 */
@Setter
@Getter
@ApiModel
public class UserSignQueryRspBody {

    @ApiModelProperty("连续签到次数")
    private int signMaxCnt;
    @ApiModelProperty("最近一周签到情况 key=日期， value=True/False")
    private Map<String, Boolean> signDataThisWeek;
}
