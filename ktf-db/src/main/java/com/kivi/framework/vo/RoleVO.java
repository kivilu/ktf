package com.kivi.framework.vo;

import java.io.Serializable;
import java.util.Set;

import com.kivi.framework.util.kit.DateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Short             num;

    private Long              pid;

    private String            name;

    private Long              deptId;

    private String            tips;

    private DateTime          gmtCreate;

    private DateTime          gmtUpdate;

    private String            checked;

    private Integer           version;

    private Set<String>       permissions;
}
