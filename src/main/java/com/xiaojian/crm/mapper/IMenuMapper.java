package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Menu;

import java.util.List;

public interface IMenuMapper {
    // 查询父节点
    List<Menu> findParentNode();
    // 根据 id 查询
    List<Menu> findById(Integer in);
}
