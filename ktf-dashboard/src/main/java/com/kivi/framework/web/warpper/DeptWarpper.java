package com.kivi.framework.web.warpper;

import java.util.List;
import java.util.Map;

import com.kivi.framework.util.kit.ObjectKit;
import com.kivi.framework.vo.DeptVO;
import com.kivi.framework.web.constant.WebConst;
import com.kivi.framework.web.constant.factory.ConstantFactory;

/**
 * 部门列表的包装
 *
 */
public class DeptWarpper extends BaseControllerWarpper<List<Map<String, Object>>> {

    public DeptWarpper( List<DeptVO> list ) {
        super(list);
    }

    @Override
    public void warpTheMap( Map<String, Object> map ) {

        Long pid = (Long) map.get(WebConst.ATTR_PID);

        if (ObjectKit.isEmpty(pid) || pid.equals(0)) {
            map.put(WebConst.ATTR_PNAME, "--");
        }
        else {
            map.put(WebConst.ATTR_PNAME, ConstantFactory.me().getDeptName(pid));
        }
    }

}
