package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.mapper.EmployeeMapper;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.EmployeeQueryObject;
import com.xiaojian.crm.service.IEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteById(String[] ids) {
        // 删除总数
        int count = 0;
        for(int i = 0 ; i < ids.length ; i++){
            employeeMapper.deleteById(Integer.parseInt(ids[i]));
            count++;
        }
        return count;
    }

    @Override
    public int save(Employee employee) {
        int count = 0;
        if(employee.getId() == null){
            employee.setPassword("123456");
            employee.setState(0);
            employee.setAdmin(0);
            count = employeeMapper.add(employee);
        } else {
            count =  employeeMapper.updateById(employee);
            // 更新员工信息,建立员工-角色关系,先删除关系
            employeeMapper.delRelationRole(employee.getId());
        }

        List<Role> roleList = employee.getRoleList();
        if(roleList != null || roleList.size() != 0){
            // 添加员工-角色关系
            for(Role role : roleList){
                employeeMapper.addRelationRole(employee.getId(),role.getId());
                count++;
            }
        }
        return count;
    }

    @Override
    public Employee findById(Integer id) {
        return employeeMapper.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public List<Integer> queryRoleOfEmp(Integer id) {
        return employeeMapper.queryRoleOfEmp(id);
    }

    @Override
    public Employee loadToLogin(String username, String password) {
        return employeeMapper.loadToLogin(username, password);
    }

    @Override
    public PageResult queryForPage(EmployeeQueryObject qo) {
        // 查询总记录数
        Long total = employeeMapper.queryCount(qo);
        if (total == null) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        // 查询总结果集
        List employeeList = employeeMapper.queryForPage(qo);

        return new PageResult(total, employeeList);
    }
}
