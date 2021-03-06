package com.kivi.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( value = "BaseReq", description = "接口请求基础Bean" )
public class BaseReq<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
                       position = 1,
                       value = "接口版本 ",
                       required = true,
                       dataType = "String",
                       notes = "接口版本 ",
                       example = "1.0.0" )
    @NotEmpty
    @Size( max = 8, message = "版本号长度最大为8" )
    private String            version;

    @ApiModelProperty(
                       position = 2,
                       value = "请求流水号 ",
                       required = false,
                       dataType = "String",
                       notes = "请求流水号",
                       example = "yyyymmddhhmmss000001" )
    @Size( max = 30, message = "请求流水号长度最大为30" )
    private String            reqSeqId;

    @ApiModelProperty(
                       position = 3,
                       value = "请求报文内容",
                       required = true,
                       dataType = "String",
                       notes = "请求报文内容",
                       example = "" )
    private T                 reqBody;
}
