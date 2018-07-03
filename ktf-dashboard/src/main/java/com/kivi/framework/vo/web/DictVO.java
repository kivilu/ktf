package com.kivi.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DictVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              pid;

    private String            name;

    private String            tips;

    private String            value;

    private Integer           num;

    private Date              gmtCreate;

    private Date              gmtUpdate;

}
