package com.kivi.framework.dao;

import com.kivi.framework.db.datascope.DataScope;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;
import com.kivi.framework.vo.web.UserVO;

public interface UserDao {

    /**
     * 根据用户账号查询用户信息
     * 
     * @param id
     * @return
     */
    UserVO getUserByPk( long id );

    /**
     * 根据用户账号查询用户信息
     * 
     * @param acctount
     * @return
     */
    UserVO getUserByAccount( String account );
    
    
    /**
     * 根据用户账号查询用户信息和密钥
     * @return
     */
    UserVO getByAccountWithPwd( String account);

    /**
     * 根据条件查询用户列表
     * 
     * @param dataScope
     * @param name
     * @param beginTime
     * @param endTime
     * @param deptid
     * @return
     */
    PageInfoKtf<UserVO> listPageUsers( DataScope dataScope, String name,
            String beginTime, String endTime, Long deptid, PageReqVO pageReq );
}
