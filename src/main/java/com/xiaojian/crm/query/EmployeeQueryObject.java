package com.xiaojian.crm.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 员工查询数据类
 */
@Setter@Getter
public class EmployeeQueryObject extends QueryObject{
    // 关键字查询
    private String keyword;

}
