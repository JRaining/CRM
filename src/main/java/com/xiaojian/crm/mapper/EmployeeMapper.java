package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
//    根据主键id删除员工
    int deleteById(Integer id);
//    添加员工
    int add(Employee employee);
//    员工-角色建立关系
    int addRelationRole(@Param("empId")Integer empId,@Param("roleId")Integer roleId);
//    员工-角色删除关系
    int delRelationRole(Integer empId);
//    根据主键id查找员工
    Employee findById(Integer id);
//    根据 姓名 查询员工并包括角色
    Employee findByName(String name);
//    查找所有员工
    List<Employee> findAll();
//    查找该员工拥有的角色 id
    List<Integer> queryRoleOfEmp(Integer id);
//    修改员工信息
    int updateById(Employee employee);
//    登录，检测用户名，密码
    Employee loadToLogin(@Param("username") String username,@Param("password") String password);
//    查询员工总数
    Long queryCount(EmployeeQueryObject qo);
//    分页查询员工信息列表
    List<Employee> queryForPage(EmployeeQueryObject qo);
}
