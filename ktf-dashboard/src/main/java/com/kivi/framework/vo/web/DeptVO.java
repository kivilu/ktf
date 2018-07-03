package com.kivi.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeptVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long              id;

    private Short             num;

    private Long              pid;

    private String            pids;

    private String            simplename;

    private String            fullname;

    private String            tips;

    private Date              gmtCreate;

    private Date              gmtUpdate;

    private Integer           version;

}
