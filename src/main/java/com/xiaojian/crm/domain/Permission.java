package com.xiaojian.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限类
 */
@Setter@Getter
public class Permission {
    private Integer id; // 主键
    private String name; // 权限名
    private String resource;  // 资源路径
}
