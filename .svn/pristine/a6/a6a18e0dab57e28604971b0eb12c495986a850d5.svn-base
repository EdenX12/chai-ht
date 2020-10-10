package com.tian.sakura.cdd.security.handler;

import com.tian.sakura.cdd.common.dto.ResultDto;
import com.tian.sakura.cdd.common.util.CommonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 咸鱼
 * @date 2019-06-04 19:58
 */
@Component
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CommonUtils.writeResponse(response, ResultDto.success("注销成功！"));
    }
}
