package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.constant.state.IsMenu;
import com.kivi.framework.vo.web.ResourceVO;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 菜单列表的包装类
 *
 */
public class MenuWarpper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public MenuWarpper( List<ResourceVO> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        map.put(WebConst.ATTR_STATUSNAME,
                ConstantFactory.me().getMenuStatusName(((Short) map.get(WebConst.ATTR_STATUS)).intValue()));
        map.put(WebConst.ATTR_IS_MENU_NAME, IsMenu.valueOf(((Short) map.get(WebConst.ATTR_IS_MENU)).intValue()));
    }

}
