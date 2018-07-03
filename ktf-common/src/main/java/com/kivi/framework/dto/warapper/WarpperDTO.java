package com.kivi.framework.dto.warapper;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO包装类
 * 
 * @author Eric
 *
 */
@Setter
@Getter
public class WarpperDTO<T, K> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户端IP
     */
    private String            clientIp;

    /**
     * 调用发起应用名称
     */
    private String            fromAppName;

    /**
     * 交易代码
     */
    private String            tranCode;

    /**
     * 异步响应对象
     */
    private K                 deferred;

    /**
     * DTO
     */
    private T                 dto;

}
