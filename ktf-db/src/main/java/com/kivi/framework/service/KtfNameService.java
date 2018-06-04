package com.kivi.framework.service;

/**
 * service 命名服务
 * 
 * @author Eric
 *
 */
public interface KtfNameService {

    /**
     * 获取当前服务的序号
     * 
     * @return
     */
    int index ( );

    /**
     * 获取与当前服务同名的服务数量
     * 
     * @return
     */
    int countOnline ( );

    /**
     * 获取当前服务的名称和ID
     * 
     * @return name-sid
     */
    String nameAndId ( );

    /**
     * 获取30长的唯一序列号
     * 
     * @return
     */
    String getUniqueqId ( );
}
