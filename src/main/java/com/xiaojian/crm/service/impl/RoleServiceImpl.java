package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Permission;
import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.mapper.PermissionMapper;
import com.xiaojian.crm.mapper.RoleMapper;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;
import com.xiaojian.crm.query.QueryObject;
import com.xiaojian.crm.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public PageResult queryPerOfRole(PermissionQueryObject qo) {
        PageResult result = null;
        Long total = permissionMapper.queryCount(qo);
        if (total == 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List perList = permissionMapper.queryForPage(qo);
        return new PageResult(total, perList);
    }

    @Override
    public int save(Role role) {
        int count = 0;
        // 如果有id值，更新角色
        if (role.getId() != null) {
            count = roleMapper.update(role);
            // 更新前，先删除角色所有权限
            roleMapper.delPerOfRole(role.getId());
        } else{
        // 如果没有id值，新增角色
            count = roleMapper.add(role);
        }
        for (Permission p : role.getPermissionList()) {
            roleMapper.roleIncreasesPer(role.getId(), p.getId());
            count++;
        }
        return count;
    }

    @Override
    public int delete(Integer id) {
        // 角色-权限有关联关系
        // 要先删除角色-权限的关联关系
        roleMapper.delPerOfRole(id);

        return roleMapper.delete(id);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public PageResult queryForPage(QueryObject qo) {
        Long total = roleMapper.queryCount(qo);
        if (total == 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        // 查询分页结果集
        List permissionList = roleMapper.queryForPage(qo);
        return new PageResult(total, permissionList);
    }
}
