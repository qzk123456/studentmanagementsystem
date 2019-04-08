package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qf.dao.ClassMapper;
import com.qf.entity.Class;
import com.qf.sevice.IClassService;
import com.qf.sevice.IStuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassMapper classMapper;
    @Reference
    private IStuService stuService;

    /**
     * 查询所有班级信息
     * @return
     */
    @Override
    public List<Class> queryAllClass() {
        return classMapper.selectList(null);
    }

    /**
     * 添加班级信息
     * @param c
     * @return
     */
    @Override
    public int addClass(Class c) {
        return classMapper.insert(c);
    }

    /**
     * 回显班级信息
     * @param id
     * @return
     */
    @Override
    public Class queryOneClassById(Integer id) {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return classMapper.selectOne(queryWrapper);
    }

    /**
     * 修改班级信息
     * @param c
     * @return
     */
    @Override
    public int updateClass(Class c) {
        return classMapper.updateById(c);
    }

    /**
     * 删除班级信息
     * @param id
     * @return
     */
    @Override
    public int deleteClassById(Integer id) {
        Class c = classMapper.selectById(id);
        UpdateWrapper<Class> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("flag", 0).set("sum",0).eq("id", id);
        int count = classMapper.update(c,updateWrapper);
        if (c.getSum()!=0&&count>0) {
            stuService.batchUpdate(c.getId());
        }
        return count;
    }

    //显示存在的班级的下拉框
    @Override
    public List<Class> queryAllClasses() {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag",1);
        return classMapper.selectList(queryWrapper);
    }


}
