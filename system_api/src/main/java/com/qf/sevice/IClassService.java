package com.qf.sevice;

import com.qf.entity.Class;

import java.util.List;

/**
 *
 *班级的增删改查
 *
 */
public interface IClassService {

    //显示所有班级信息
    List<Class> queryAllClass();
    //增加班级信息
    int addClass(Class c);
    //回显班级信息
    Class queryOneClassById(Integer id);
    //修改班级信息
    int updateClass(Class c);
    //删除班级信息
    int deleteClassById(Integer id);
    //班级下拉框
    List<Class> queryAllClasses();

}
