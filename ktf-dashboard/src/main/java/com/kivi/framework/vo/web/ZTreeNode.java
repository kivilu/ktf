package com.kivi.framework.vo.web;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * jquery ztree 插件的节点
 * 
 */
@Setter
@Getter
public class ZTreeNode {

    private Long    id;     // 节点id

    private Long    pId;    // 父节点id

    private String  name;   // 节点名称

    private Boolean open;   // 是否打开节点

    private Boolean checked;// 是否被选中

    public Boolean getOpen() {
        return open;
    }

    public void setOpen( Boolean open ) {
        this.open = open;
    }

    public Boolean getIsOpen() {
        return open;
    }

    public void setIsOpen( Boolean open ) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked( Boolean checked ) {
        this.checked = checked;
    }

    public static ZTreeNode createParent() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setPId(0L);
        return zTreeNode;
    }
}
