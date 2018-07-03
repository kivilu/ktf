package com.kivi.framework.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.model.KtfSysParam;
import com.kivi.framework.service.KtfSysParamService;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.vo.KtfSysParamVO;

@Service
public class KtfSysParamServiceImpl extends BaseDao<KtfSysParam> implements KtfSysParamService {

    @Cacheable( value = KtfCache.SysParam.PARAM_STR, key = "#customCode+#bizCode+#paramCode" )
    @Override
    public String getParamVal( String customCode, String bizCode, String paramCode ) {
        KtfSysParam entity = new KtfSysParam();
        entity.setCustomCode(customCode);
        entity.setBizCode(bizCode);
        entity.setParamCode(paramCode);

        KtfSysParam result = super.selectOne(entity);
        if (result == null)
            return "";

        return result.getParamValue();
    }

    @Cacheable( value = KtfCache.SysParam.PARAM_INT, key = "#customCode+#bizCode+#paramCode" )
    @Override
    public Integer getParamValInt( String customCode, String bizCode, String paramCode ) {
        String val = this.getParamVal(customCode, bizCode, paramCode);
        return Integer.parseInt(val);
    }

    @Cacheable( value = KtfCache.SysParam.PARAM_LONG, key = "#customCode+#bizCode+#paramCode" )
    @Override
    public Long getParamValLong( String customCode, String bizCode, String paramCode ) {
        String val = this.getParamVal(customCode, bizCode, paramCode);
        return Long.parseLong(val);
    }

    @Override
    public KtfSysParamVO saveParam( KtfSysParamVO vo ) {
        KtfSysParam entity = new KtfSysParam();
        BeanKit.copyProperties(vo, entity);
        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());

        super.saveNotNull(entity);
        return vo;
    }

    @CacheEvict( allEntries = true )
    @Override
    public KtfSysParamVO updateParam( KtfSysParamVO vo ) {

        KtfSysParam entity = new KtfSysParam();
        BeanKit.copyProperties(vo, entity);
        entity.setGmtUpdate(DateTimeKit.date());

        super.updateNotNull(entity);

        return vo;
    }

}
