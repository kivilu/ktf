package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 部门列表的包装
 *
 */
public class NoticeWrapper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public NoticeWrapper( List<?> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        Long creater = (Long) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
