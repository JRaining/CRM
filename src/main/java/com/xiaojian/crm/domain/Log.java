package com.xiaojian.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 日志类
 */
@Setter@Getter
public class Log {
    private Integer id; //主键
    private Employee opUser;    //操作者
    private Date opTime;    // 操作时间
    private String opIp;    // 操作ip地址
    private String url;     // 操作的访问地址
    private String function; // 操作方法
    private String params;  // 操作参数

}
