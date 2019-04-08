package com.qf.sevice;

import com.qf.common.ClassVo;
import com.qf.common.StudentVo;
import com.qf.entity.Student;

import java.util.List;

/**
 *
 *学生的增删改查
 *
 */
public interface IStuService {

    //显示所有学生信息
    List<StudentVo> queryAllStudent();
    //增加学生信息
    int addStudent(Student student);
    //回显学生信息
    StudentVo queryOneStudentById(Integer id);
    //修改学生信息
    int updateStudent(Student student);
    //删除学生信息
    int deleteStudentById(Integer id);
    //班级下拉列表
    List<ClassVo> getClasses();
    //批量修改班级id
    void batchUpdate(Integer classId);
}
