package com.kivi.framework.web.constant.tips;

import com.kivi.framework.component.ErrorKit;
import com.kivi.framework.exception.WebBizExceptionEnum;

/**
 * 返回给前台的错误提示
 *
 */
public class ErrorTip extends Tip {

    public ErrorTip( String code, String message ) {
        super();
        this.code = code;
        this.message = message;
        this.httpStatus = 200;
    }

    public ErrorTip( int code, String message ) {
        this(String.valueOf(code), message);

    }

    public ErrorTip( WebBizExceptionEnum e ) {
        this(String.valueOf(e.getCode()), e.getMessage());

    }

    public ErrorTip( String code ) {
        this(String.valueOf(code), ErrorKit.me().getDesc(code));
    }

}
