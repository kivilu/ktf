package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.dao.DeptDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfDeptMapperEx;
import com.kivi.framework.persist.model.KtfDept;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.vo.DeptVO;
import com.kivi.framework.vo.ZTreeNode;

import tk.mybatis.mapper.entity.Example;

@Repository
public class DeptDaoImpl extends BaseDao<KtfDept> implements DeptDao {

    @Autowired
    private KtfDeptMapperEx ktfDeptMapperEx;

    @Transactional
    @Override
    public void deleteDept( Long deptId ) {
        // 删除子部门
        Example example = new Example(KtfDept.class);
        example.createCriteria().andLike("pids", "%[" + deptId + "]%");
        super.mapper.deleteByExample(example);

        // 删除id指定部门
        super.delete(deptId);
    }

    @Override
    public List<ZTreeNode> tree() {
        return ktfDeptMapperEx.tree();
    }

    @Override
    public List<DeptVO> list( String condition ) {
        List<DeptVO> list = ktfDeptMapperEx.list(condition);

        return list;
    }

    @CachePut( value = KtfCache.Dashboard.DEPT, key = "caches[0].name+'_'+#dept.id" )
    @Override
    public DeptVO saveNotNull( DeptVO dept ) {
        KtfDept entity = new KtfDept();

        BeanKit.copyProperties(dept, entity);

        super.saveNotNull(entity);

        dept.setId(entity.getId());

        return dept;
    }

    @CacheEvict( value = KtfCache.Dashboard.DEPT, key = "caches[0].name+'_'+#dept.id" )
    @Override
    public DeptVO updateNotNull( DeptVO dept ) {

        KtfDept entity = new KtfDept();

        BeanKit.copyProperties(dept, entity);
        super.updateNotNull(entity);
        return dept;
    }

    @Cacheable( value = KtfCache.Dashboard.DEPT, key = "caches[0].name+'_'+#id" )
    @Override
    public DeptVO getDept( Long id ) {
        KtfDept entity = super.selectByKey(id);
        if (entity == null)
            return null;

        DeptVO dept = new DeptVO();
        BeanKit.copyProperties(entity, dept);
        return dept;
    }

    @Override
    public List<DeptVO> listSubDept( Long pid ) {

        Example example = new Example(KtfDept.class);
        example.createCriteria().andLike("pids", "%[" + pid + "]%");

        List<KtfDept> depts = super.selectByExample(example);

        List<DeptVO> result = depts.stream().map(r-> {
            return Convert.convertObject(r, DeptVO.class);
        })
                .collect(Collectors.toList());

        return result;
    }

}
