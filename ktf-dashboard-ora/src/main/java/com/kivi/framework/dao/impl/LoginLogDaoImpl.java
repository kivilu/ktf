package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.LoginLogDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfLoginLogMapperEx;
import com.kivi.framework.persist.model.KtfLoginLog;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.LoginLogVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

@Repository
public class LoginLogDaoImpl extends BaseDao<KtfLoginLog> implements LoginLogDao {

    @Autowired
    private KtfLoginLogMapperEx ktfLoginLogMapperEx;

    public int saveNotNull( Map<String, Object> map ) {
        KtfLoginLog entity = BeanKit.mapToBean(map, KtfLoginLog.class);

        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());

        return super.mapper.insertSelective(entity);
    }

    @Override
    public PageInfoKtf<LoginLogVO> listPage( String beginTime, String endTime,
            String logName, PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());

        List<KtfLoginLog> list = ktfLoginLogMapperEx.getLoginLogs(beginTime, endTime, logName, pageReq.getSort(),
                pageReq.isAsc());

        List<LoginLogVO> result = list.stream().map(r->{return Convert.convertObject(r, LoginLogVO.class);})
        		.collect(Collectors.toList());
        
        return new PageInfoKtf<>(new PageInfo<>(result));
    }

    @Override
    public void deleteAll() {
        mapper.delete(new KtfLoginLog());
    }

}
