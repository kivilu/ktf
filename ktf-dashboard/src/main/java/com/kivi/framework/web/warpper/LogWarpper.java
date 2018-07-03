package com.kivi.framework.web.warpper;

import java.util.Map;

import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;
import com.kivi.framework.web.util.Contrast;

/**
 * 日志列表的包装类
 *
 */
public class LogWarpper extends BaseControllerWarpper<Map<String, Object>> {

    public LogWarpper( Map<String, Object> obj ) {
        super(obj);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        String message = (String) map.get(WebConst.ATTR_MESSAGE);

        Long userid = (Long) map.get(WebConst.ATTR_USERID);
        map.put(WebConst.ATTR_USERNAME, ConstantFactory.me().getUserNameById(userid));

        // 如果信息过长,则只截取前100位字符串
        if (StrKit.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put(WebConst.ATTR_MESSAGE, subMessage);
        }

        // 如果信息中包含分割符号;;; 则分割字符串返给前台
        if (StrKit.isNotEmpty(message) && message.indexOf(Contrast.separator) != -1) {
            String[] msgs = message.split(Contrast.separator);
            map.put("regularMessage", msgs);
        }
        else {
            map.put("regularMessage", message);
        }
    }

}
