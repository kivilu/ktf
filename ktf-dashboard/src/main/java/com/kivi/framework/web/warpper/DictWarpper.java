package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.web.DictVO;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 字典列表的包装
 *
 */
public class DictWarpper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public DictWarpper( List<DictVO> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        StringBuffer detail = new StringBuffer();
        Long id = (Long) map.get(WebConst.ATTR_ID);
        List<DictVO> dicts = ConstantFactory.me().findInDict(id);
        if (dicts != null) {
            for (DictVO dict : dicts) {
                detail.append(dict.getValue() + ":" + dict.getName() + ",");
            }
            map.put(WebConst.ATTR_DETAIL, StrKit.removeSuffix(detail.toString(), ","));
        }
    }

}
