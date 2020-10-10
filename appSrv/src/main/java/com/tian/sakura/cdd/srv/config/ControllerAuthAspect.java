package com.tian.sakura.cdd.srv.config;

import com.tian.sakura.cdd.common.annotation.CustomerApiAuth;
import com.tian.sakura.cdd.common.dict.EUserType;
import com.tian.sakura.cdd.common.exception.BizRuntimeException;
import com.tian.sakura.cdd.common.resp.RespCodeEnum;
import com.tian.sakura.cdd.common.web.ResponseMessage;
import com.tian.sakura.cdd.common.web.ResponseMessageBuilder;
import com.tian.sakura.cdd.db.domain.user.SUser;
import com.tian.sakura.cdd.db.manage.user.SUserManage;
import com.tian.sakura.cdd.srv.filter.LoginUserThreadLocal;
import com.tian.sakura.cdd.srv.service.login.CustTokenService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * controller层请求授权
 *
 * @author lvzonggang
 */
@Aspect
@Configuration
public class ControllerAuthAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustTokenService authTokenService;
    @Autowired
    private SUserManage userManage;

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
    @Pointcut("execution(public * com.tian.sakura.cdd.srv.web..*.*Controller.*(..))")
    public void excudeService() {

    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //从请求头中解析token， 如果存在token，校验真实性
        doHolderLoginUser(request);

        //获取连接点目标类名
        String targetName = pjp.getTarget().getClass().getName();
        //获取连接点签名的方法名
        String methodName = pjp.getSignature().getName();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //处理方法权限
        CustomerApiAuth apiAuth = signature.getMethod().getDeclaredAnnotation(CustomerApiAuth.class);
        if (apiAuth == null) {
            logger.debug("类[{}]的方法[{}]上没有添加任何授权限制", targetName, methodName);
            return pjp.proceed();
        }
        //获取方法授权角色
        EUserType[] userTypes = apiAuth.value();
        if (userTypes.length == 0) {
            return pjp.proceed();
        }
        List<EUserType> userTypeList = Arrays.asList(userTypes);
        if (userTypeList.contains(EUserType.NONE)) {
            return pjp.proceed();
        }

        //获取登录账号信息
        SUser loginUser = LoginUserThreadLocal.getLoginUser();
        if (loginUser == null) {
            throw new BizRuntimeException(RespCodeEnum.NO_LOGIN);
        }
        EUserType loginUserType = EUserType.getUserType(loginUser.getUserType());

        if (!userTypeList.contains(loginUserType)) {
            logger.warn("登录用户类型[{}],方法需要的用户类型[{}]", loginUserType, Arrays.deepToString(userTypes));
            throw new BizRuntimeException(RespCodeEnum.NO_ACCESS_RIGHTS);
        }

        Object obj = pjp.proceed();

        //释放threadLocal用户
        LoginUserThreadLocal.removeLoginUser();
        return obj;
    }

    private void doHolderLoginUser(HttpServletRequest request ) {
        String headerToken = request.getHeader("token");
        if (StringUtils.isEmpty(headerToken)){
            //throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_REQUIRED);
            return;
        }
        Map<String,Object> result = authTokenService.validateToken(headerToken);
        if (!(boolean)result.get("token")) {
            throw new BizRuntimeException(RespCodeEnum.FAIL_TOKEN_EXPIRED);
        } else {
            String bizCode = (String) result.get("bizCode");
            String role = (String)result.get("bizType");
            LoginUserThreadLocal.putLoginUser(userManage.getUserByPhone(bizCode));
        }
    }
}
