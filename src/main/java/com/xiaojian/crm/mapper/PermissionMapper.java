package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Permission;
import com.xiaojian.crm.query.PermissionQueryObject;

import java.util.List;


public interface PermissionMapper {
//    查询所有权限
    List<Permission> findAll();
//    id查询权限
    Permission findById(Integer id);
//    查询权限总数
    Long queryCount(PermissionQueryObject qo);
//    分页查询权限
    List<Permission> queryForPage(PermissionQueryObject qo);

    List<String> queryByEid(Integer id);
}
