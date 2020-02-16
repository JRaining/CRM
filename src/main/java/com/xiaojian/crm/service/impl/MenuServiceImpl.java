package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Menu;
import com.xiaojian.crm.mapper.IMenuMapper;
import com.xiaojian.crm.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    @Resource
    private IMenuMapper menuMapper;

    @Override
    public List<Menu> findParentNode() {
        return menuMapper.findParentNode();
    }

    @Override
    public List<Menu> findById(Integer id) {
        return menuMapper.findById(id);
    }
}
