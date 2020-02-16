package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Department;

import java.util.List;

/**
 * 部门类 dao
 */
public interface DepartmentMapper {
    // id查询部门
    Department findById(Integer id);
// 查询所有部门
    List<Department> findAll();
    // 新增部门
    int add(Department department);
    // 删除部门
    int delete(Integer id);
    // 修改部门信息
    int update(Department department);

    // 查询所有部门id,名称
    List<Department> queryForEmp();
}
