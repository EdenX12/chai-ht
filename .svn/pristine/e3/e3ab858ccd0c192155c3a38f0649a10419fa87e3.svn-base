package com.tian.sakura.cdd.common.web.filter;

import com.tian.sakura.cdd.common.util.IdGenUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * 记录日志mdc的过滤器
 *
 * @author lvzonggang
 */
public class LogMdcFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("THREAD_ID", IdGenUtil.generateId());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
