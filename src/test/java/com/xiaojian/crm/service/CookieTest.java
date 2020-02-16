package com.xiaojian.crm.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CookieTest {

    @RequestMapping("/addCookie")
    public void addCookie(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie  cookie = new Cookie("id","123");
        // -1 默认，只存在浏览器中
        // 0 有该cookie,删除该cookie，没有，不保存;
        // 任意值为以秒为单位的 存储时间
        cookie.setMaxAge(60 * 6);

        response.addCookie(cookie);
        System.out.println("已存入cookie！");
        response.getWriter().print("已存入cookie！");
    }

    @RequestMapping("/getCookie")
    public void getCookie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        String sessionId = request.getSession().getId();
        System.out.println("sessionId:" + sessionId);
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("JSESSIONID")){
                    System.out.println("cookie的id的值为：" + cookies[i].getValue());
                    response.getWriter().print("cookie的id的值为：" + cookies[i].getValue());
                }
            }
        }
    }
}
