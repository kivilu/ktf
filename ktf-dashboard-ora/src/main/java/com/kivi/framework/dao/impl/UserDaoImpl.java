package com.kivi.framework.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.UserDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.db.datascope.DataScope;
import com.kivi.framework.persist.mapper.KtfUserMapperEx;
import com.kivi.framework.persist.model.KtfUser;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.vo.UserVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

@Repository
public class UserDaoImpl extends BaseDao<KtfUser> implements UserDao {

    @Autowired
    private KtfUserMapperEx ktfUserMapperEx;

    // @Cacheable( value = KtfCache.Dashboard.USER_BY_ID, key = "#id" )
    @Override
    public UserVO getUserByPk( long id ) {
        KtfUser user = super.selectByKey(id);
        if (user == null)
            return null;

        UserVO vo = new UserVO();

        BeanKit.copyProperties(user, vo);

        return vo;
    }

    // @Cacheable( value = KtfCache.Dashboard.USER_BY_ACCOUNT, key = "#acctount"
    // )
    @Override
    public UserVO getUserByAccount( String acctount ) {

        UserVO vo = ktfUserMapperEx.getByAccount(acctount);
        return vo;
    }

    @Override
    public PageInfoKtf<UserVO> listPageUsers( DataScope dataScope, String name,
            String beginTime, String endTime, Long deptid, PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());

        List<UserVO> list = ktfUserMapperEx.selectUsers(dataScope, name, beginTime, endTime, deptid);
        return new PageInfoKtf<>(new PageInfo<>(list));
    }

    @Override
    public KtfUser updateNotNull( KtfUser entity ) {
        entity.setGmtUpdate(DateTimeKit.date());
        return super.updateNotNull(entity);
    }

    @Override
    public UserVO getByAccountWithPwd( String account ) {

        KtfUser query = new KtfUser();
        query.setAccount(account);

        KtfUser user = super.selectOne(query);

        return Convert.convertObject(user, UserVO.class);
    }

    @Override
    public int save( UserVO user ) {
        KtfUser entity = Convert.convertObject(user, KtfUser.class);
        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());

        super.saveNotNull(entity);
        return 1;
    }

    @Override
    public int update( UserVO user ) {
        KtfUser entity = Convert.convertObject(user, KtfUser.class);
        entity.setGmtUpdate(DateTimeKit.date());

        super.saveNotNull(entity);
        return 1;
    }

    @Override
    public int delete( Long id ) {
        return super.delete(id);
    }

}
