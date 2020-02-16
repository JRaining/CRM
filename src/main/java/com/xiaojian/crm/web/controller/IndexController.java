package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.domain.Menu;
import com.xiaojian.crm.page.AjaxResult;
import com.xiaojian.crm.service.IEmployeeService;
import com.xiaojian.crm.service.IMenuService;
import com.xiaojian.crm.service.IPermissionService;
import com.xiaojian.crm.util.PermissionUtil;
import com.xiaojian.crm.util.UserConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页、登录验证
 */
@Controller
public class IndexController {

    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IMenuService menuService;

//    请求到index.jsp页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 登录，并把用户信息存入session
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request){
        // 将 request 放入当前线程,只能用于login的整个操作
        UserConst.set(request);

        HttpSession session = request.getSession();
        AjaxResult result = null;
        Employee employee = employeeService.loadToLogin(username,password);
        if(employee != null){
            // session存入该员工信息
            session.setAttribute(UserConst.USER_IN_SESSION,employee);
            result = new AjaxResult(true);
            // 根据员工id查询所拥有权限集合，存入session
            List<String> empPermissions = permissionService.queryByEid(employee.getId());
            session.setAttribute(UserConst.PERMISSION_IN_SESSION,empPermissions);
            // 把用户能访问的 右侧菜单存入 session
            List<Menu> menuListInSession = menuService.findParentNode();
            PermissionUtil.checkMenuPermission(menuListInSession);
            session.setAttribute(UserConst.MENU_IN_SESSION,menuListInSession);
        } else{
            result = new AjaxResult("用户名或密码错误!");
        }
        return result;
    }

    @RequestMapping("/findMenu")
    @ResponseBody
    public List<Menu> findMenu(){
        List<Menu> menuList =  (List<Menu>)UserConst.get().getSession().getAttribute(UserConst.MENU_IN_SESSION);
        return menuList;
    }
}
