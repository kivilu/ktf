package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kivi.framework.dao.NoticeDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfNoticeMapperEx;
import com.kivi.framework.persist.model.KtfNotice;
import com.kivi.framework.util.Convert;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

@Repository
public class NoticeDaoImpl extends BaseDao<KtfNotice> implements NoticeDao {

    @Autowired
    KtfNoticeMapperEx noticeMapperEx;

    @Override
    public PageInfoKtf<KtfNoticeVO> listPage( String content, PageReqVO pageReq ) {

        int page = pageReq.getOffset() / pageReq.getLimit() + 1;
        PageHelper.startPage(page, pageReq.getLimit());

        List<KtfNotice> list = noticeMapperEx.list(content);
        
        List<KtfNoticeVO> result = list.stream().map(r->{return Convert.convertObject(r, KtfNoticeVO.class);})
        		.collect(Collectors.toList());
        
        return new PageInfoKtf<>(new PageInfo<>(result));
    }

	@Override
	public KtfNoticeVO getByPk(Long id) {
		KtfNotice vo =  super.selectByKey(id);
		
		return Convert.convertObject(vo, KtfNoticeVO.class);
	}

}
