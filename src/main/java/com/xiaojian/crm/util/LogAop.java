package com.xiaojian.crm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.domain.Log;
import com.xiaojian.crm.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 */
public class LogAop {

    @Resource
    private ILogService logService;

    public void doAfter(JoinPoint joinPoint) throws NoSuchMethodException {
        // 避免自己切自己的情况
        if(joinPoint.getTarget() instanceof ILogService){
            return;
        }
// 3.1 方法名
        String methodName = joinPoint.getSignature().getName();
        // 登录方法不切
        if(methodName == "login"){
            return;
        }

        Log log = new Log();
        // 设置访问时间
        log.setOpTime(new Date());

// 1.获取用户名，需要获取request,再获取session
        HttpServletRequest request = UserConst.get();
        // 1.1获取用户
        Employee employee = (Employee)request.getSession().getAttribute(UserConst.USER_IN_SESSION);
        log.setOpUser(employee);

// 2.获取访问者的ip地址
        String ip = request.getRemoteAddr();
        log.setOpIp(ip);

// 3.获取访问的方法
        Method method = null;
        Class clazz = joinPoint.getTarget().getClass();
        // 3.1 获取方法参数
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            method = clazz.getMethod(methodName);
        } else{
            Class[] classArgs = new Class[args.length];
            for(int i = 0 ; i < args.length ; i++){
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }

        // 设置访问方法 全限定类名：方法名
        log.setFunction(clazz.toString() + " : " + methodName);

// 4.获取请求传入方法的的参数
        // 把参数对象转换为json对象
        ObjectMapper mapper = new ObjectMapper();
        // 把参数对象转换为json个数的字符串:mapper = ["admin","1"]
        String paramValue;
        try {
            paramValue = mapper.writeValueAsString(args);
            // 设置参数
            log.setParams(paramValue);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//  5.获取访问路径
        String url = "";
        // 5.1 获取类上的 @RequestMapping("/..")

        if (clazz != null && method != null && clazz != LogAop.class) {
            String classValue = "";
            String methodValue = "";
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                classValue = classAnnotation.value()[0];
            }
            // 5.2 获取访问方法上的 @RequestMapping("/..")
            RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
            if(methodAnnotation != null){
                methodValue = methodAnnotation.value()[0];
                url = classValue + methodValue;
                log.setUrl(url);
            }
        }

        logService.add(log);

    }

}
