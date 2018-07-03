package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.vo.web.RoleVO;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 角色列表的包装类
 *
 */
public class RoleWarpper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public RoleWarpper( List<RoleVO> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        map.put(WebConst.ATTR_PNAME, ConstantFactory.me().getSingleRoleName((Long) map.get(WebConst.ATTR_PID)));
        map.put(WebConst.ATTR_DEPTNAME, ConstantFactory.me().getDeptName((Long) map.get(WebConst.ATTR_DEPTID)));
    }

}
