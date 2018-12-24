package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.KtfOperationLogDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfOperationLogMapperEx;
import com.kivi.framework.persist.model.KtfOperationLog;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.vo.OperationLogVO;
import com.kivi.framework.vo.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

@Repository
public class KtfOperationLogDaoImpl extends BaseDao<KtfOperationLog> implements KtfOperationLogDao {

    @Autowired
    private KtfOperationLogMapperEx ktfOperationLogMapperEx;

    public int saveNotNull( Map<String, Object> map ) {
        KtfOperationLog entity = BeanKit.mapToBean(map, KtfOperationLog.class);

        return super.mapper.insertSelective(entity);
    }

    @Override
    public PageInfoKtf<OperationLogVO> listPage( String beginTime,
            String endTime, String logName, String logType,
            PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());

        List<KtfOperationLog> list = ktfOperationLogMapperEx.getOperationLogs(beginTime, endTime, logName, logType,
                pageReq.getSort(), pageReq.isAsc());

        List<OperationLogVO> result = list.stream().map(r-> {
            return Convert.convertObject(r, OperationLogVO.class);
        })
                .collect(Collectors.toList());

        return new PageInfoKtf<OperationLogVO>(new PageInfo<>(result));

    }

    @Override
    public void deleteAll() {
        mapper.delete(new KtfOperationLog());
    }

    @Override
    public OperationLogVO getByKey( Long id ) {
        KtfOperationLog entity = super.selectByKey(id);
        return Convert.convertObject(entity, OperationLogVO.class);
    }

}
