package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.page.AjaxResult;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;
import com.xiaojian.crm.query.QueryObject;
import com.xiaojian.crm.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {

    @Resource
    private IRoleService roleService;

    @RequestMapping("/role")
    public String index(){
        return "role";
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public PageResult roleList(QueryObject qo){
        PageResult result = roleService.queryForPage(qo);
        return result;
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public AjaxResult save(Role role){
        int count = roleService.save(role);
        if(count > 1){
            return new AjaxResult(true,"保存成功!");
        } else{
            return new AjaxResult("保存失败!");
        }
    }

    @RequestMapping("/openEdit")
    @ResponseBody
    public PageResult openEdit(PermissionQueryObject qo){

        PageResult result = roleService.queryPerOfRole(qo);
        return result;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxResult delete(Integer id){
        AjaxResult result = null;
        int count = roleService.delete(id);
        if(count > 0){
           return new AjaxResult(true,"删除成功!");
        }
        return new AjaxResult("删除失败，请联系管理员!");
    }

    @RequestMapping("/queryRoleToEmp")
    @ResponseBody
    public List<Role> queryRoleToEmp(){

        return roleService.findAll();
    }
}
