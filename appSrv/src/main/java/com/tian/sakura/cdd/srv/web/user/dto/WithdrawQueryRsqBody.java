package com.tian.sakura.cdd.srv.web.user.dto;

import com.tian.sakura.cdd.common.entity.BasePage;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 说明。
 *
 * @author lvzonggang
 */
@ApiModel
@Setter
@Getter
public class WithdrawQueryRsqBody {

    List<WithdrawRspBody> withdrawRspBodyList;
}
