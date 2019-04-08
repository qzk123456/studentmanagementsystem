package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 *
 */
public interface StuMapper extends BaseMapper<Student> {
    void batchUpdate(Integer classId);
}
