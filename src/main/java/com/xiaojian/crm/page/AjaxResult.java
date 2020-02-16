package com.xiaojian.crm.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 异步请求返回数据
 */
@Setter@Getter
public class AjaxResult {
    private boolean success;
    private String msg;

    public AjaxResult(boolean success) {
        this.success = success;
    }

    public AjaxResult(String msg) {
        this.msg = msg;
    }

    public AjaxResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
