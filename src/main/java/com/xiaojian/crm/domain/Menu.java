package com.xiaojian.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 右侧栏树结构目录
 */
@Setter@Getter
public class Menu {
    private Integer id; // 主键 id
    private String text; // 显示的节点文本
    private String iconCls; // 显示的节点图标CSS类ID。
    private boolean checked; // 该节点是否被选中
    private String state; // 节点状态，'open' 或 'closed'
    private String attributes; // 绑定该节点的自定义属性

    private List<Menu> children; // 子节点
}
