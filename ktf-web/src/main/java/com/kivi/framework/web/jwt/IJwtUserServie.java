package com.kivi.framework.web.jwt;

public interface IJwtUserServie {

    /**
     * 用户密码凭证
     * 
     * @param identifier
     * @return
     */
    String getCredential( String identifier );

    /**
     * 用户密码凭证
     * 
     * @param id
     *            主键
     * @return
     */
    String getCredential( Long userId );

    /**
     * 用户已经退出
     * 
     * @param id
     */
    void logouted( Long userId );
}
