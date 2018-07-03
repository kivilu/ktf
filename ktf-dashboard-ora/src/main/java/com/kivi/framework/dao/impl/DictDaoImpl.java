package com.kivi.framework.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.DictDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.exception.WebBizException;
import com.kivi.framework.exception.WebBizExceptionEnum;
import com.kivi.framework.persist.mapper.KtfDictMapperEx;
import com.kivi.framework.persist.model.KtfDict;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.MutiStrKit;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;
import com.kivi.framework.vo.web.DictVO;

import tk.mybatis.mapper.entity.Example;

@Repository
public class DictDaoImpl extends BaseDao<KtfDict> implements DictDao {

    @Autowired
    private KtfDictMapperEx                                                 ktfDictMapperEx;

    private static ConcurrentSkipListMap<String, List<Map<String, Object>>> subDictValueMap = new ConcurrentSkipListMap<>();

    @Override
    public void addDict( String dictName, String dictTips, String dictValues ) {
        // 判断有没有该字典
        Example example = new Example(KtfDict.class);
        example.createCriteria().andEqualTo("name", dictName).andEqualTo("pid", 0);
        List<KtfDict> dicts = super.mapper.selectByExample(example);
        if (dicts != null && dicts.size() > 0) {
            throw new WebBizException(WebBizExceptionEnum.DICT_EXISTED);
        }

        // 解析dictValues
        List<Map<String, String>> items = MutiStrKit.parseKeyValue(dictValues);

        // 添加字典
        KtfDict dict = new KtfDict();
        dict.setName(dictName);
        dict.setTips(dictTips);
        dict.setValue("0");
        dict.setPid(0L);
        dict.setNum(0);
        dict.setGmtCreate(DateTimeKit.date());
        dict.setGmtUpdate(dict.getGmtCreate());
        super.saveNotNull(dict);

        // 添加字典条目
        int num = 1;
        for (Map<String, String> item : items) {
            String value = item.get(MutiStrKit.MUTI_STR_KEY);
            String name = item.get(MutiStrKit.MUTI_STR_VALUE);
            KtfDict itemDict = new KtfDict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            itemDict.setValue(value);
            itemDict.setGmtCreate(DateTimeKit.date());
            itemDict.setGmtUpdate(itemDict.getGmtCreate());
            itemDict.setNum(num++);
            super.saveNotNull(itemDict);
        }

    }

    @Override
    public void editDict( Long dictId, String dictName, String dictTips, String dicts ) {
        // 删除之前的字典
        this.delteDict(dictId);

        // 重新添加新的字典
        this.addDict(dictName, dictTips, dicts);
    }

    @Override
    public void delteDict( Long dictId ) {

        subDictValueMap.clear();

        // 删除这个字典的子词典
        Example example = new Example(KtfDict.class);
        example.createCriteria().andEqualTo("pid", dictId);
        super.mapper.deleteByExample(example);
        // 删除这个词典
        super.delete(dictId);

    }

    @Override
    public DictVO getDict( Long id ) {
        DictVO vo = new DictVO();

        KtfDict entity = super.selectByKey(id);

        BeanKit.copyProperties(entity, vo);

        return vo;
    }

    @Override
    public List<DictVO> listSubDict( Long pid ) {
        // List<DictVO> result = new ArrayList<>();
        KtfDict qryDict = new KtfDict();
        qryDict.setPid(pid);
        List<KtfDict> list = super.selectByEntity(qryDict);

        List<DictVO> result = list.stream().map(dict-> Convert.convertObject(dict, DictVO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        /*
         * Iterator<KtfDict> it = list.iterator(); while (it.hasNext()) { DictVO
         * vo = new DictVO(); KtfDict entity = it.next();
         * BeanKit.copyProperties(entity, vo); result.add(vo); }
         */

        return result;
    }

    @Override
    public List<DictVO> selectByName( String name ) {
        return ktfDictMapperEx.selectByName(name);
    }

    @Override
    public List<Map<String, Object>> listDictValues( String name ) {
        if (subDictValueMap.containsKey(name))
            return subDictValueMap.get(name);

        List<Map<String, Object>> list = ktfDictMapperEx.listDictValues(name);
        List<Map<String, Object>> values = list.stream().map(item-> {
            Set<String> keys = CollectionKit.newHashSet(item.keySet());
            for (String key : keys) {
                item.put(key.toLowerCase(), item.remove(key));
            }
            return item;
        }).collect(Collectors.toList());

        subDictValueMap.put(name, values);

        return values;
    }

    @Override
    public PageInfoKtf<DictVO> listPage( String conditiion, PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());
        List<DictVO> list = ktfDictMapperEx.list(conditiion);

        return new PageInfoKtf<DictVO>(new PageInfo<>(list));
    }

}
