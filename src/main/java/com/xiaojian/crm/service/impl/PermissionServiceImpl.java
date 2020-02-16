package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Permission;
import com.xiaojian.crm.mapper.PermissionMapper;
import com.xiaojian.crm.page.PageResult;
import com.xiaojian.crm.query.PermissionQueryObject;
import com.xiaojian.crm.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public Permission findById(Integer id) {
        return permissionMapper.findById(id);
    }

    @Override
    public PageResult queryForPage(PermissionQueryObject qo) {

        Long total = permissionMapper.queryCount(qo);
        if(total == 0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        // 查询分页结果集
        List permissionList = permissionMapper.queryForPage(qo);
        return new PageResult(total,permissionList);

    }

    @Override
    public List<String> queryByEid(Integer id) {
        return permissionMapper.queryByEid(id);
    }
}
