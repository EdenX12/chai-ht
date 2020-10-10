package com.tian.sakura.cdd.srv.web.task;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.task.ShareService;
import com.tian.sakura.cdd.srv.web.product.dto.PrdShareRspBody;
import com.tian.sakura.cdd.srv.web.task.dto.ShareNoticeReq;
import com.tian.sakura.cdd.srv.web.task.dto.SharePrdReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 分享产品api
 *
 * @author lvzonggang
 */

@RestController
@RequestMapping("share")
@Api("分享商品API")
public class ShareProductController {

    @Autowired
    private ShareService shareService;

    @ApiOperation("分享产品")
    @PostMapping("product")
    public Map<String,String> shareProduct(@RequestBody @Valid SharePrdReq sharePrdReq) {
        String shareId = shareService.shareProduct(sharePrdReq);

        Map<String, String> result = new HashMap<>();
        result.put("shareId", shareId);

        return result;
    }

    @ApiOperation("分享结果通知")
    @PostMapping("notice")
    public void notice(@RequestBody @Valid ShareNoticeReq shareNoticeReq) {
        shareService.notice(shareNoticeReq);
    }

    @ApiOperation("点击立刻分享按钮")
    @PostMapping("shareBtnClick")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
    public PrdShareRspBody shareBtnClick(@RequestBody @Valid SharePrdReq sharePrdReq) {
    	SUser user = LoginUserThreadLocal.getLoginUser();
    	PrdShareRspBody rsp = shareService.shareBtnClick(sharePrdReq, user.getUnionId());
    	return rsp;
    }

}
