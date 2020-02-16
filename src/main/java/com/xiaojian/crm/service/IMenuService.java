package com.xiaojian.crm.service;

import com.xiaojian.crm.domain.Menu;

import java.util.List;

public interface IMenuService {
    // 查询父节点
    List<Menu> findParentNode();
    // 根据 id 查询
    List<Menu> findById(Integer in);
}
