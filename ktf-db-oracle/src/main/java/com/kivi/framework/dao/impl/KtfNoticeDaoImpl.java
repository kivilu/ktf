package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.KtfNoticeDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfNoticeMapperEx;
import com.kivi.framework.persist.model.KtfNotice;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

@Repository
public class KtfNoticeDaoImpl extends BaseDao<KtfNotice> implements KtfNoticeDao {

    @Autowired
    KtfNoticeMapperEx noticeMapperEx;

    @Override
    public PageInfoKtf<KtfNoticeVO> listPage( String content, PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());

        List<KtfNotice> list = noticeMapperEx.list(content);

        List<KtfNoticeVO> result = list.stream().map(r-> {
            return Convert.convertObject(r, KtfNoticeVO.class);
        })
                .collect(Collectors.toList());

        return new PageInfoKtf<>(new PageInfo<>(result));
    }

    @Override
    public KtfNoticeVO getByPk( Long id ) {
        KtfNotice vo = super.selectByKey(id);

        return Convert.convertObject(vo, KtfNoticeVO.class);
    }

    @Override
    public List<KtfNoticeVO> listAll() {
        List<KtfNotice> list = super.selectByEntity(new KtfNotice());

        List<KtfNoticeVO> result = list.stream().map(r-> {
            return Convert.convertObject(r, KtfNoticeVO.class);
        })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public KtfNoticeVO saveNotNull( KtfNoticeVO vo ) {
        KtfNotice entity = Convert.convertObject(vo, KtfNotice.class);

        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());

        super.saveNotNull(entity);
        vo.setId(entity.getId());
        return vo;
    }

    @Override
    public int delete( Long key ) {
        return super.delete(key);

    }

    @Override
    public int updateNotNull( KtfNoticeVO vo ) {
        KtfNotice entity = Convert.convertObject(vo, KtfNotice.class);

        entity.setGmtUpdate(DateTimeKit.date());
        super.updateNotNull(entity);
        return 1;
    }

}
