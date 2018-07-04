package com.kivi.framework.dao;

import java.util.List;

import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

public interface KtfNoticeDao {

    KtfNoticeVO getByPk( Long id );

    List<KtfNoticeVO> listAll();

    PageInfoKtf<KtfNoticeVO> listPage( String content, PageReqVO pageReq );

    KtfNoticeVO saveNotNull( KtfNoticeVO vo );

    int updateNotNull( KtfNoticeVO vo );

    int delete( Long key );

}
