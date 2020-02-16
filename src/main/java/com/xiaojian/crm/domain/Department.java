package com.xiaojian.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门类
 */
@Setter @Getter
public class Department {
    private Integer id;     //主键
    private String sn;      //部门标号
    private String name;    //部门名称
    private Employee manager;  //部门经理
    private Department parent;    //上级部门
    private Integer state;  //部门状态：0 正常，-1 停用
}
