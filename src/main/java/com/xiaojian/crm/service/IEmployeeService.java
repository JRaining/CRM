package com.xiaojian.crm.service;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
//    根据主键id删除员工，修改员工状态
    int deleteById(String[] ids);
//    根据主键id查找员工
    Employee findById(Integer id);
//    查找所有员工
    List<Employee> findAll();
//    查找该员工拥有的角色 id
    List<Integer> queryRoleOfEmp(Integer id);
//    添加、修改员工信息
    int save(Employee employee);
//    登录验证
    Employee loadToLogin(String username,String password);
//    分页查询员工信息
    PageResult queryForPage(EmployeeQueryObject qo);
}
