/**
 * 
 */
package com.kivi.framework.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.constant.GlobalErrorConst;
import com.kivi.framework.dao.KtfSysParamDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.exception.DaoException;
import com.kivi.framework.persist.model.KtfSysParam;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.vo.KtfSysParamVO;

/**
 * 系统参数DAO实现
 * 
 * @author Eric
 *
 */
@Repository
public class KtfSysParamDaoImpl extends BaseDao<KtfSysParam> implements KtfSysParamDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.kivi.framework.db.dao.IDaoEx#saveNotNull(java.util.Map)
     */
    @CacheEvict( value = KtfCache.SysParam.PARAM, allEntries = true )
    public int saveNotNull( Map<String, Object> map ) {
        try {
            KtfSysParam entity = BeanKit.mapToBean(map, KtfSysParam.class);
            super.saveNotNull(entity);
        }
        catch (Exception e) {
            throw new DaoException(GlobalErrorConst.E_DB_INSERT, "保存系统参数异常");
        }

        return 1;
    }

    @Override
    @Cacheable( value = KtfCache.SysParam.PARAM, key = "caches[0].name+'_'+#key" )
    public KtfSysParam selectByKey( Object key ) {
        return super.selectByKey(key);
    }

    @Override
    @CacheEvict( value = KtfCache.SysParam.PARAM, allEntries = true )
    public KtfSysParam saveNotNull( KtfSysParam entity ) {
        return super.saveNotNull(entity);
    }

    @Override
    @CacheEvict( value = KtfCache.SysParam.PARAM, allEntries = true )
    public int delete( Object key ) {
        return super.delete(key);
    }

    @Override
    @CacheEvict( value = KtfCache.SysParam.PARAM, allEntries = true )
    public KtfSysParam updateNotNull( KtfSysParam entity ) {
        return super.updateNotNull(entity);
    }

    @Override
    @CacheEvict( value = KtfCache.SysParam.PARAM, allEntries = true )
    public int updateNotNull( KtfSysParam condEntity, KtfSysParam updateEntity ) {
        return super.updateNotNull(condEntity, updateEntity);
    }

    @Override
    @Cacheable( value = KtfCache.SysParam.PARAM, key = "caches[0].name+'_'+#customCode+#bizCode+#paramCode" )
    public KtfSysParamVO getKtfSysParam( String customCode, String bizCode, String paramCode ) {
        KtfSysParam entity = new KtfSysParam();
        entity.setCustomCode(customCode);
        entity.setBizCode(bizCode);
        entity.setParamCode(paramCode);

        KtfSysParam r = super.selectOne(entity);
        if (r == null)
            return null;

        return Convert.convertObject(r, KtfSysParamVO.class);
    }

    @Cacheable( value = KtfCache.SysParam.PARAM, key = "caches[0].name+'_'+#customCode+#bizCode" )
    @Override
    public String listKtfSysParamValue( String customCode, String bizCode ) {
        Map<String, Object> map = new HashMap<String, Object>();

        KtfSysParam entity = new KtfSysParam();
        entity.setCustomCode(customCode);
        entity.setBizCode(bizCode);

        List<KtfSysParam> list = super.selectByEntity(entity);
        Iterator<KtfSysParam> it = list.iterator();
        while (it.hasNext()) {
            KtfSysParam param = it.next();
            if ("0".equals(param.getParamType()))
                map.put(param.getParamCode(), param.getParamValue());
            else
                map.put(param.getParamCode(), Integer.parseInt(param.getParamValue()));
        }

        return JSON.toJSONString(map);
    }

}
