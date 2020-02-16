package com.xiaojian.crm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户session常量
 */
public class UserConst {
    public static final String USER_IN_SESSION = "USER_IN_SESSION_XIAOJIAN";
    public static final String PERMISSION_IN_SESSION = "PERMISSION_IN_SESSION";
    public static final String MENU_IN_SESSION = "MENU_IN_SESSION";


    public static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static void set(HttpServletRequest request){
        local.set(request);
    }

    public static HttpServletRequest get(){
        return local.get();
    }
}
