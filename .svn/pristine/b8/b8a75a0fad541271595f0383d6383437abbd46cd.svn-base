package com.tian.sakura.cdd.srv.filter;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.web.ResponseMessage;
import com.tian.sakura.cdd.common.web.ResponseMessageBuilder;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.service.login.CustTokenService;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * api令牌过滤器
 *
 * @author lvzonggang
 * @Date 2018/5/10
 */

public class ApiTokenFilter implements Filter {

    private transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustTokenService authTokenService;
    @Autowired
    private SUserManage userManage;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
    	logger.info(StreamUtils.copyToString(servletRequest.getInputStream(), Charset.forName(Charsets.UTF_8.name())));

    	//Cookie tokenCookie = CookieManager.getCookieByName((HttpServletRequest) servletRequest,"token");
        String headerToken = ((HttpServletRequest) servletRequest).getHeader("token");
        if (StringUtils.isEmpty(headerToken)){
            ResponseMessage baseResponse =
                    ResponseMessageBuilder.instance().fail(
                            RespCodeEnum.FAIL_TOKEN_REQUIRED.getRespCode(),RespCodeEnum.FAIL_TOKEN_REQUIRED.getRespMsg());
            writeResponse(baseResponse, servletResponse);
            return;
        }
        Map<String,Object> result = authTokenService.validateToken(headerToken);
        if (!(boolean)result.get("token")) {
            ResponseMessage baseResponse = ResponseMessageBuilder.instance().fail
                    (RespCodeEnum.FAIL_TOKEN_EXPIRED.getRespCode(),RespCodeEnum.FAIL_TOKEN_EXPIRED.getRespMsg());
            writeResponse(baseResponse, servletResponse);
        } else {
            String bizCode = (String) result.get("bizCode");
            String role = (String)result.get("bizType");

            LoginUserThreadLocal.putLoginUser(userManage.getUserByPhone(bizCode));
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    private void writeResponse(ResponseMessage baseResponse, ServletResponse servletResponse) {
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = servletResponse.getWriter();
            out.append(JSON.toJSONString(baseResponse));
            logger.debug("返回是" + JSON.toJSONString(baseResponse));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
