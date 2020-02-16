package com.xiaojian.crm.service;

import com.xiaojian.crm.domain.Permission;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;

import java.util.List;

public interface IPermissionService {
    //    查询所有权限
    List<Permission> findAll();
    //    id查询权限
    Permission findById(Integer id);
    //    分页查询权限
    PageResult queryForPage(PermissionQueryObject qo);

    List<String> queryByEid(Integer id);
}
