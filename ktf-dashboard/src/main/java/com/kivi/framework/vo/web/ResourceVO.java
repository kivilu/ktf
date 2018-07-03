package com.kivi.framework.vo.web;

import java.util.ArrayList;
import java.util.List;

import com.kivi.framework.util.kit.DateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceVO implements Comparable<ResourceVO> {

    /**
     * 节点id
     */
    private Long             id;

    /**
     * 父节点
     */
    private Long             parentId;

    private String           code;

    private String           pcode;

    private String           pcodes;

    private String           name;

    private String           icon;

    private String           url;

    private Short            num;

    private Short            levels;

    private Short            isMenu;

    private String           tips;

    private Short            status;

    private Short            isOpen;

    private DateTime         createTime;

    private DateTime         updateTime;

    private String           checked;

    /**
     * 子节点的集合
     */
    private List<ResourceVO> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<ResourceVO> linkedList = new ArrayList<ResourceVO>();

    public ResourceVO() {
        super();
    }

    public ResourceVO( Long id, Long parentId ) {
        super();
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public int compareTo( ResourceVO o ) {
        // TODO Auto-generated method stub
        return 0;
    }
}
