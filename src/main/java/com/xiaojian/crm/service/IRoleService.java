package com.xiaojian.crm.service;

import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;
import com.xiaojian.crm.query.QueryObject;

import java.util.List;

public interface IRoleService {
    // 查询所有角色
    List<Role> findAll();
    //    id查询角色
    Role findById(Integer id);
    // 查询该角色拥有的权限
    PageResult queryPerOfRole(PermissionQueryObject qo);
    //    增加新角色
    int save(Role role);
    //    删除角色
    int delete(Integer id);
    //    修改角色信息
    int update(Role role);
    //    分页查询角色信息
    PageResult queryForPage(QueryObject qo);
}
