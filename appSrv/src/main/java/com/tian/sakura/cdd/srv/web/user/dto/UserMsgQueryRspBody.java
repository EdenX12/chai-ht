package com.tian.sakura.cdd.srv.web.user.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter

@Builder
public class UserMsgQueryRspBody {

    private PageInfo pageInfo;
}
