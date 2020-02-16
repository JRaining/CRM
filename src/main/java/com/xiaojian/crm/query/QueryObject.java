package com.xiaojian.crm.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 查询数据类
 */
@Setter@Getter
public class QueryObject {
    private Integer page;   //当前页
    private Integer rows;   //行数

 //查询开始数
    public Integer getStart() {
        if(this.page != null && this.rows != null){
            return (this.page - 1) * this.rows;
        }
        return 0;
    }
}
