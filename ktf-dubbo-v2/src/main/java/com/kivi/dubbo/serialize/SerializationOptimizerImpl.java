package com.kivi.dubbo.serialize;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.kivi.framework.component.SpringContextHolder;
import com.kivi.framework.dto.BaseReq;
import com.kivi.framework.dto.BaseReqDTO;
import com.kivi.framework.dto.BaseResDTO;
import com.kivi.framework.dto.BaseRsp;
import com.kivi.framework.dto.PagerDTO;
import com.kivi.framework.dto.SvrStatusReqDTO;
import com.kivi.framework.dto.SvrStatusRspDTO;
import com.kivi.framework.dto.warapper.WarpperDTO;
import com.kivi.framework.dto.warapper.WarpperReqDTO;
import com.kivi.framework.dto.warapper.WarpperRspDTO;
import com.kivi.framework.properties.KtfDubboProperties;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.page.PageInfoBT;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageInfoVO;
import com.kivi.framework.vo.page.PageReqVO;

public class SerializationOptimizerImpl implements SerializationOptimizer {
    private static final Logger log = LoggerFactory.getLogger(SerializationOptimizerImpl.class);

    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();

        classes.add(WarpperDTO.class);
        classes.add(WarpperReqDTO.class);
        classes.add(WarpperRspDTO.class);
        classes.add(BaseReq.class);
        classes.add(BaseRsp.class);
        classes.add(BaseReqDTO.class);
        classes.add(BaseResDTO.class);
        classes.add(PagerDTO.class);
        classes.add(SvrStatusReqDTO.class);
        classes.add(SvrStatusRspDTO.class);
        classes.add(PageInfoBT.class);
        classes.add(PageInfoKtf.class);
        classes.add(PageInfoVO.class);
        classes.add(PageReqVO.class);

        KtfDubboProperties ktfDubboProperties = SpringContextHolder.getBeanNoAssert(KtfDubboProperties.class);
        if (ktfDubboProperties == null) {
            return classes;
        }

        String[] classNames = StrKit.split(ktfDubboProperties.getSerializeClasses(), ",");
        if (classNames == null)
            return classes;
        for (String clazz : classNames) {
            try {
                classes.add(Class.forName(clazz));
            }
            catch (ClassNotFoundException e) {
                log.error("类名找不到", e);
            }
        }

        return classes;
    }

}