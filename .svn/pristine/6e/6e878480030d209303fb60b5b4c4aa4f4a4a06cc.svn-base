package com.tian.sakura.cdd.srv.web.login;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.db.manage.params.ParamsManage;
import com.tian.sakura.cdd.srv.web.login.dto.*;
import com.tian.sakura.cdd.srv.GlobalConstants;
import com.tian.sakura.cdd.srv.service.login.CustTokenService;
import com.tian.sakura.cdd.srv.service.login.LoginService;
import com.tian.sakura.cdd.wx.message.WxUser;
import com.tian.sakura.cdd.wx.service.WxAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录
 *
 * @author lvzonggang
 * @Date 2019/8/17
 */
@RestController
@RequestMapping("login")
@Api("登录模块")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;
    @Autowired
    private CustTokenService custTokenService;
    @Autowired
    private WxAuthService wxAuthService;
    
    @Autowired
    private ParamsManage paramsManage;


    /**
     *  手机号 + 密码登录。
     *  如果手机号为注册手机号，直接登录；
     *  如果手机号为新的手机号，注册该客户
     *   <p>手机号+短信验证码登录,不需要强制绑定微信</p>
     *
     *   </p>
     * @param loginReq
     * @param response
     * @return
     */
    @PostMapping("phone")
    @ApiOperation("手机+动态码登录")
    public LoginRsp loginByPhone(@RequestBody @Validated PhoneLoginReq loginReq, HttpServletResponse response) {
        LoginRsp loginRsp = loginService.loginByPhone(loginReq);
        //jwt token
        loginRsp.setToken(doToken(loginRsp.getPhone(), response));
        return loginRsp;
    }

    /**
     * 微信登录
     *
     *
     * @param wxLoginReq
     * @param response
     * @return
     */
    @PostMapping("wechat")
    @ApiOperation("微信登录")
    public LoginRsp loginByWx(@RequestBody @Validated WxLoginReq wxLoginReq, HttpServletResponse response) {
        WxUser wxUser = doGetWxUser(wxLoginReq.getBody().getCode());
        String headImg = wxUser.getHeadimgurl();
        if(StringUtils.isNotBlank(headImg)) {
        	headImg = headImg.replace("http://", "https://");
        	wxUser.setHeadimgurl(headImg);
        }
        
        LoginRsp loginRsp = loginService.loginByWx(wxLoginReq, wxUser);
        // jwt token
        if (loginRsp.getCheckPhone()) {
            loginRsp.setToken(doToken(loginRsp.getPhone(), response));
        }
        return loginRsp;
    }

    private WxUser doGetWxUser(String code) {
        //TODO 从配置表中获取该参数
        //WxUser wxUser = buildTestWxUser();
        String appid = paramsManage.getValueByKey(GlobalConstants.PARAM_KEY_OPEN_APP_ID);
        String secret = paramsManage.getValueByKey(GlobalConstants.PARAM_KEY_OPEN_APP_SECRET);
        
        WxUser wxUser = wxAuthService.loginApp(appid,secret,code);
        logger.info("微信信息[{}]", JSON.toJSONString(wxUser));
        return wxUser;
    }
    private WxUser buildTestWxUser() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("openid-test");
        wxUser.setUnionid("unionid-test");
        return wxUser;
    }

    /**
     *  创建token
     * @param custNo 手机号
     * @param response
     */
    private String doToken(String custNo, HttpServletResponse response) {
        String token = custTokenService.geneToken(custNo);
        response.addCookie(new Cookie("token", token));
        response.setHeader("token", token);
        return token;
    }

    /**
     * 验证手机号是否存在
     *
     * @param phoneVerifyReq
     * @return
     */
    @PostMapping("/phoneVerify")
    @ApiOperation("校验注册手机号")
    public Object phoneVerify(@RequestBody PhoneVerifyReq phoneVerifyReq) {
        return loginService.phoneVerify(phoneVerifyReq);
    }

    /**
     * 微信登录-绑定手机
     *
     * @param req
     * @return
     */
    @PostMapping("/bindPhone")
    @ApiOperation("微信登录-绑定手机号")
    public LoginRsp bindPhone(@RequestBody @Valid PhoneBindWeChatReq req, HttpServletResponse response) {
        LoginRsp loginRsp =  loginService.bindPhone(req);

        loginRsp.setToken(doToken(loginRsp.getPhone(), response));

        return loginRsp;
    }

    /**
     * 手机登录-绑定微信
     *
     * TODO  APP绑定微信 和 公众号绑定微信 都需要调用该接口？
     *
     * @param req
     * @return
     */
    @Deprecated
    @PostMapping("/bindWeChat")
    @ApiOperation("手机登录-绑定微信")
    public Object bindWeChat(@RequestBody @Valid BindWeChatReq req) {
        throw new BizRuntimeException(RespCodeEnum.CALL_METHOD_FAIL);
        //return loginService.bindWeChat(req, doGetWxUser(req.getBody().getCode()));
    }

}
