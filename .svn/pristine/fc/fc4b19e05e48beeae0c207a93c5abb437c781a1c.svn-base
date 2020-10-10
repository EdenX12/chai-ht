package com.tian.sakura.cdd.srv.filter;

import com.tian.sakura.cdd.db.domain.user.SUser;

/**
 * 登录用户 threadLocal
 *
 * @author lvzonggang
 */
public class LoginUserThreadLocal {

    private static ThreadLocal<SUser> loingUserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> loginIpThreadLocal = new ThreadLocal<>();

    public static void putLoginUser(SUser loginUser) {
        loingUserThreadLocal.set(loginUser);
    }

    public static SUser getLoginUser() {
        return loingUserThreadLocal.get();
    }

    public static void removeLoginUser() {
        loingUserThreadLocal.remove();
    }

    public static void putIp(String ip) {
        loginIpThreadLocal.set(ip);
    }

    public static void removeIp(){
        loginIpThreadLocal.remove();
    }

    public static String getIp(){
        return loginIpThreadLocal.get();
    }


}
