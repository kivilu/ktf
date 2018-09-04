package com.kivi.framework.dao;

import java.util.List;

import com.kivi.framework.vo.DeptVO;
import com.kivi.framework.vo.ZTreeNode;

public interface DeptDao {

    /**
     * 获取ztree的节点列表
     *
     * @return
     * @date 2017年2月17日 下午8:28:43
     */
    List<ZTreeNode> tree();

    /**
     * 根据条件查询
     * 
     * @param condition
     * @return
     */
    List<DeptVO> list( String condition );
    
    /**
     * 查询子部门
     * @param deptid
     * @return
     */
    List<DeptVO> listSubDept( Long pid );

    /**
     * 删除部门及其下属部门
     * 
     * @param deptId
     */
    void deleteDept( Long deptId );

    /**
     * 保存部门对象
     * 
     * @param dept
     * @return
     */
    DeptVO saveNotNull( DeptVO dept );

    /**
     * 更新部门
     * 
     * @param dept
     * @return
     */
    DeptVO updateNotNull( DeptVO dept );

    /**
     * 根据主键ID查询对象
     * 
     * @param id
     * @return
     */
    DeptVO getDept( Long id );
}
