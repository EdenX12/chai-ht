package com.tian.sakura.cdd.console.config;

import com.alibaba.fastjson.JSON;
import com.tian.sakura.cdd.common.annotation.NotAopLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller层请求参数，返回参数打印日志
 *
 * @author lvzonggang
 */
@Aspect
@Configuration
public class ControllerLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com.tian.sakura.cdd.console.web..*.*Controller.*(..))")
    public void excudeService() {

    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //获取连接点目标类名
        String targetName = pjp.getTarget().getClass().getName();
        //获取连接点签名的方法名
        String methodName = pjp.getSignature().getName();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        NotAopLog aopLog = signature.getMethod().getDeclaredAnnotation(NotAopLog.class);
        if (aopLog != null) {
            return pjp.proceed();
        }

        String url = "";
        String method = "";
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            url = request.getRequestURL().toString();
            method = request.getMethod();
            Object[] args = pjp.getArgs();
            String params = "";

            logger.info("请求开始===地址:{},方法：{},类名：{}，方法：{}", new Object[]{url, method, targetName, methodName});
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {

                int i = 1;
                for (Object obj : args) {
                    if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse) {
                        //暂不处理对象HttpServletRequest
                        continue;
                    }
                    params = JSON.toJSONString(obj);
                    logger.info("请求开始===参数[" + i++ + "]:" + params);
                }
            }
        } catch (Exception e) {

        }
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        try {
            //logger.info("请求开始===地址:{},方法：{},类名：{}，方法：{}" , new Object[]{url, method, targetName, methodName});
            logger.info("请求结束===返回值:" + JSON.toJSONString(result));
        } catch (Exception e) {

        }
        return result;
    }


}
