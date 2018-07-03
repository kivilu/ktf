package com.kivi.framework.dao;

import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

public interface NoticeDao {
	
	KtfNoticeVO getByPk(Long id) ;

    PageInfoKtf<KtfNoticeVO> listPage( String content, PageReqVO pageReq );

}
