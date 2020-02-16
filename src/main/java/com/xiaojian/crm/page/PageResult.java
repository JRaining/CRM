package com.xiaojian.crm.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 返回到页面的数据类
 */
@Setter
@Getter
public class PageResult {
    private Long total; //总条数
    private List rows;  //结果集


    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
