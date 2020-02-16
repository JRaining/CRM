package com.xiaojian.crm.util;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.domain.Menu;
import com.xiaojian.crm.domain.Permission;
import com.xiaojian.crm.service.IPermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
@Component
public class PermissionUtil {
    /*
     * Spring确实能给对象属性注入值
     * 但我们必须搞明白，什么是对象属性？非静态字段！！
     * 静态字段属于类，不属于对象。
     * 不信的话，大家可以创建一个Person类，设定两个字段 静态的age、非静态的name。
     * 结果你debug观察person对象只能看到name！
     * */
    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService){
        PermissionUtil.permissionService = permissionService;
    }

    public static boolean checkPermission(String function){
        // 超级管理员，不受权限控制
        HttpSession session = UserConst.get().getSession();
        Employee emp = (Employee)session.getAttribute(UserConst.USER_IN_SESSION);
        if(emp.getAdmin() == 1){
            return true;
        }
        // 拿到 function 去所有权限中查询，看该权限在所有权限集合中是否有数据
        // 如果没有，不需要权限控制，放行
        // 如果有，该地址需要权限控制
            // 进行匹配
            // ALL权限匹配

        // 先获取所有权限信息
        if(CommonUtil.allPermissions.size() == 0){
            List<Permission> permissionList = permissionService.findAll();
            for(Permission p : permissionList){
                CommonUtil.allPermissions.add(p.getResource());
            }
        }

        if(CommonUtil.allPermissions.contains(function)){
            // 如果有，该地址需要权限控制
            // 1. 先拿到该用户所有的权限集合，从session获取
            List<String> userPermissions = (List<String>)session.getAttribute(UserConst.PERMISSION_IN_SESSION);
                // 进行完全匹配
            if(userPermissions.contains(function)){
                return true;
            } else{
                // ALL权限匹配(这里没有使用)
                String allPermission = function.split(":")[0] + ":ALL";
                if(userPermissions.contains(allPermission)){
                    return true;
                } else{
                    return false;
                }
            }
        } else{
            // 如果没有，不需要权限控制，放行
            return true;
        }
    }

    /**
     * 如果用户没有相应的菜单操作权限，就从列表中删除
     * @param menuList
     * @return
     */
    public static List<Menu> checkMenuPermission(List<Menu> menuList){
        Menu menu;
        // 用户所拥有的权限
        HttpSession session = UserConst.get().getSession();
        List<String> empPermission = (List<String>) session.getAttribute(UserConst.PERMISSION_IN_SESSION);
        for(int i = menuList.size() - 1; i >= 0; i--){
            menu = menuList.get(i);
            String menuPermission = menuList.get(i).getAttributes();
            // 菜单访问需要权限
            if(StringUtils.isNotBlank(menuPermission)){
                // 该用户没有该权限，就从菜单列表中移除，这样页面就不会显示了
                if(!empPermission.contains(menuPermission)){
                    menuList.remove(i);
                }
            }
            // 递归处理子菜单
            if(menu.getChildren() != null && menu.getChildren().size() > 0){
                checkMenuPermission(menu.getChildren());
            }
        }
        return menuList;
    }
}
