package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.page.AjaxResult;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.EmployeeQueryObject;
import com.xiaojian.crm.service.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class EmployeeController {

    @Resource
    private IEmployeeService employeeServie;

    @RequestMapping("/employee")
    public String index(){
        return "employee";
    }

/**
 * 员工列表，分页
 * @param qo
 * @return
         */
    @RequestMapping("/employeeList")
    @ResponseBody
    public PageResult employeeList(EmployeeQueryObject qo){
        PageResult pageResult =  employeeServie.queryForPage(qo);
        return pageResult;
    }

    @RequestMapping("/saveEmp")
    @ResponseBody
    public AjaxResult save(Employee employee) {
        AjaxResult result = null;
        int num = employeeServie.save(employee);
        if(num > 1){
            result = new AjaxResult(true,"保存成功");
        } else{
            result = new AjaxResult("保存失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/deleteEmp")
    @ResponseBody
    public AjaxResult delete(String[] ids){
        AjaxResult result = null;
        int num = employeeServie.deleteById(ids);
        if(num > 0){
            result = new AjaxResult(true,"操作成功，员工已离职!");
        } else{
            result = new AjaxResult("操作失败，请联系管理人员!");
        }
        return result;
    }

    @RequestMapping("/queryRoleOfEmp")
    @ResponseBody
    public List<Integer> queryRoleOfEmp(Integer id){
       return employeeServie.queryRoleOfEmp(id);
    }

}
