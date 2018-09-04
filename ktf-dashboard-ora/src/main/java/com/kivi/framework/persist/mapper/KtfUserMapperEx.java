package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.db.datascope.DataScope;
import com.kivi.framework.vo.UserVO;

public interface KtfUserMapperEx {

    /**
     * 根据账号查找用户
     * 
     * @param account
     * @return
     */
    UserVO getByAccount( @Param( "account" ) String account );

    /**
     * 根据账号查找用户,带密码
     * 
     * @param account
     * @return
     */
    UserVO getByAccountWithPwd( @Param( "account" ) String account );

    /**
     * 根据条件查询用户列表
     *
     */
    List<UserVO> selectUsers( @Param( "dataScope" ) DataScope dataScope, @Param( "name" ) String name,
            @Param( "beginTime" ) String beginTime, @Param( "endTime" ) String endTime,
            @Param( "deptid" ) Long deptid );

}
