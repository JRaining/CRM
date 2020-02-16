package com.xiaojian.crm.web.interceptor;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.util.PermissionUtil;
import com.xiaojian.crm.util.UserConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器取消使用ing
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 把每次通过拦截器的request存入该线程中
        UserConst.set(request);

        // 登录拦截
        HttpSession session = request.getSession();
        Employee currentUser = (Employee)session.getAttribute(UserConst.USER_IN_SESSION);
        if(currentUser == null || currentUser.getState() == 0){
            // 重定向到登录页面
            response.sendRedirect("/login.jsp");
            return false;
        }

        if(o instanceof HandlerMethod){
            // 登录后，再检验权限
            HandlerMethod handlerMethod = (HandlerMethod)o;
            String function = "";
            RequestMapping methodValue = handlerMethod.getMethod().getAnnotation(RequestMapping.class);
            if(methodValue != null){
                function = methodValue.value()[0];
            }

            // 根据权限表达式查询用户是否有该权限
            boolean flag = PermissionUtil.checkPermission(function);
            if(flag){
                return true;
            } else{
                // 获取访问返回的是什么？
                ResponseBody ajax = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
                if(ajax != null){
                    // 如果需返回ajax
                    request.getRequestDispatcher("/noPermission.json").forward(request,response);
                } else{
                    // 如果需返回页面
                    request.getRequestDispatcher("/noPermission.jsp").forward(request,response);
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
