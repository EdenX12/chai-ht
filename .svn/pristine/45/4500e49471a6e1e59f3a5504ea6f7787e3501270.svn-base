package com.tian.sakura.cdd.srv.web.user;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.user.UserInterestService;
import com.tian.sakura.cdd.srv.web.user.dto.InterestReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户关注商品/任务API
 *
 * @author lvzonggang
 */
@Api("用户关注商品/任务API")
@RestController
@RequestMapping("interest")
public class UserFollowController {

    @Autowired
    private UserInterestService interestService;

    @ApiOperation("用户关注商品api")
    @PostMapping("product")
    @CustomerApiAuth(value = {EUserType.NORMAL_CUST})
    public InterestRspBody followProduct(@RequestBody @Valid InterestReq req) {
        SUser user = LoginUserThreadLocal.getLoginUser();
        Map<String,Object> map = interestService.interestInProduct(user.getId(), req.getBody().getProductId());
        return new InterestRspBody(Integer.valueOf(map.get("cnt").toString()),
                Boolean.valueOf(map.get("focus").toString()));
    }

    @Setter
    @Getter
    @ApiModel
    public class InterestRspBody {
        @ApiModelProperty("该产品关注总计数")
        private int interestCnt;
        @ApiModelProperty("关注标识")
        private boolean focus;

        public InterestRspBody(int interestCnt) {
            this.interestCnt = interestCnt;
        }

        public InterestRspBody(int interestCnt, boolean focus) {
            this.interestCnt = interestCnt;
            this.focus = focus;
        }
    }
}
