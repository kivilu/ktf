package com.kivi.framework.dto.warapper;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarpRspDTO<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 调用响应应用名称
     */
    private String            fromAppName;

    /**
     * 交易流水号
     */
    private String            tranSeqId;

    /**
     * 参数
     */
    private Object[]          params;

    /**
     * 响应内容
     */
    private T                 rspObject;
}
