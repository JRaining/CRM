package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.domain.Department;
import com.xiaojian.crm.service.IDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class DepartmentController {
    @Resource
    private IDepartmentService departmentService;

    @RequestMapping("/queryDept")
    @ResponseBody
    public List<Department> queryForEmp(){
        return departmentService.queryForEmp();
    }
}
