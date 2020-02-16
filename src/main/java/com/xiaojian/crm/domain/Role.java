package com.xiaojian.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色类
 */
@Setter@Getter
public class Role {
    private Integer id;     // 主键
    private String sn;      // 编号
    private String name;    // 名称

    List<Permission> permissionList;
}
