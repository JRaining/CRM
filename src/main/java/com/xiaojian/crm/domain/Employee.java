package com.xiaojian.crm.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 员工类
 */
@Setter@Getter
public class Employee {

    private Integer id;         // 主键
    private String username;    //用户名
    private String realName;    //真实姓名
    private String password;    //密码
    private String tel;         //电话号码
    private String email;       //邮箱
    private Department dept;    //部门对象
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date inputTime;     //录入时间
    private Integer state;      //状态：1 正常，0 离职
    private String stateStr;    // 状态String
    private Integer admin;      //身份：1 超级管理员，0 普通
    private String adminStr;    // 身份String

    List<Role> roleList ;

    public String getStateStr() {
        //状态：1 正常，0 离职
        if(state != null){
            if(state == 1){
                stateStr = "<font color=green>正常</font>";
            } else if(state == 0){
                stateStr = "<font color=red>离职</font>";
            }
        }
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getAdminStr() {
        //身份：1 超级管理员，0 普通
        if(admin != null){
            if(admin == 1){
                adminStr = "是";
            } else if(admin == 0){
                adminStr = "否";
            }
        }
        return adminStr;
    }

    public void setAdminStr(String adminStr) {
        this.adminStr = adminStr;
    }


}
