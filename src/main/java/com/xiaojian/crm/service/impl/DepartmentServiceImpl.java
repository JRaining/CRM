package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Department;
import com.xiaojian.crm.mapper.DepartmentMapper;
import com.xiaojian.crm.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public int add(Department department) {
        return departmentMapper.add(department);
    }

    @Override
    public int delete(Integer id) {
        return departmentMapper.delete(id);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.update(department);
    }

    @Override
    public List<Department> queryForEmp() {
        return departmentMapper.queryForEmp();
    }

}
