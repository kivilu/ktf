package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.vo.web.UserVO;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 用户管理的包装类
 *
 */
public class UserWarpper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public UserWarpper( List<UserVO> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {
        map.put(WebConst.ATTR_SEXNAME,
                ConstantFactory.me().getSexName((String) map.get(WebConst.ATTR_SEX)));
        map.put(WebConst.ATTR_ROLENAME, ConstantFactory.me().getRoleName((String) map.get(WebConst.ATTR_ROLEID)));
        map.put(WebConst.ATTR_DEPTNAME, ConstantFactory.me().getDeptName((Long) map.get(WebConst.ATTR_DEPTID)));
        map.put(WebConst.ATTR_STATUSNAME,
                ConstantFactory.me().getStatusName(((Short) map.get(WebConst.ATTR_STATUS)).intValue()));
    }

}
