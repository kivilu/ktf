package com.kivi.framework.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KtfSysParamVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer           id;

    /**
     * 父ID
     */
    private Integer           pid;

    /**
     * 客户代码
     */
    private String            customCode;

    /**
     * 业务代码
     */
    private String            bizCode;

    /**
     * 参数代码
     */
    private String            paramCode;

    /**
     * 参数名称
     */
    private String            paramName;

    /**
     * 参数类型，0：字符串，1：数字，2：金额，默认：0
     */
    private String            paramType;

    /**
     * 参数值
     */
    private String            paramValue;

}
