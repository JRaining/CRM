package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;
import com.xiaojian.crm.service.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PermissionController {

    @Resource
    private IPermissionService permissionService;


    @RequestMapping("/permissionList")
    @ResponseBody
    public PageResult permissionList(PermissionQueryObject qo){
        PageResult result = permissionService.queryForPage(qo);
        return result;
    }

}
