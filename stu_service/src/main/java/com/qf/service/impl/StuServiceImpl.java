package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qf.common.ClassVo;
import com.qf.common.StudentVo;
import com.qf.dao.StuMapper;
import com.qf.entity.Class;
import com.qf.entity.Student;
import com.qf.sevice.IClassService;
import com.qf.sevice.IStuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
@Service
public class StuServiceImpl implements IStuService {

    @Autowired
    private StuMapper stuMapper;

    @Reference
    private IClassService classService;

    /**
     * 查询所有学生信息
     * @return
     */
    @Override
    public List<StudentVo> queryAllStudent() {
        List<Student> students = stuMapper.selectList(null);
        List<StudentVo> list = new ArrayList<>();
        for (Student student: students) {
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(student,studentVo);
            if (student.getClassId()>0) {
                Class c = classService.queryOneClassById(student.getClassId());
                studentVo.setClassName(c.getClassName());
            }else{
                studentVo.setClassName("无");
            }
            list.add(studentVo);
        }
        return list;
    }

    /**
     * 增加学生信息
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        int count = stuMapper.insert(student);
        if (count>0&&student.getClassId()>0) {
            Class c = classService.queryOneClassById(student.getClassId());
            c.setSum(c.getSum()+1);
            classService.updateClass(c);
        }
        return count;
    }

    /**
     * 回显学生信息
     * @param id
     * @return
     */
    @Override
    public StudentVo queryOneStudentById(Integer id) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Student s = stuMapper.selectOne(queryWrapper);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(s,studentVo);
        if (s.getClassId()>0) {
            Class c = classService.queryOneClassById(s.getClassId());
            studentVo.setClassName(c.getClassName());
        }
        return studentVo;
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", student.getId());
        int count = stuMapper.update(student, queryWrapper);
        if (count>0&&student.getClassId()>0) {
            Class c = classService.queryOneClassById(student.getClassId());
            c.setSum(c.getSum()+1);
            classService.updateClass(c);
        }
        return count;
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    @Override
    public int deleteStudentById(Integer id) {
        Student student = stuMapper.selectById(id);
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("flag",0).eq("id", id);
        int count = stuMapper.update(student,updateWrapper);
        if (student.getClassId()>0&&count>0) {
            Class c = classService.queryOneClassById(student.getClassId());
            c.setSum(c.getSum()-1);
            classService.updateClass(c);
        }
        return count;
    }

    /**
     * 获得所有班级列表
     * @return
     */
    @Override
    public List<ClassVo> getClasses() {
        List<Class> list = classService.queryAllClasses();
        List<ClassVo> classVos = new ArrayList<>();
        for (Class c: list) {
            ClassVo classVo = new ClassVo();
            BeanUtils.copyProperties(c, classVo);
            classVos.add(classVo);
        }
        return classVos;
    }

    /**
     * 批量清除classId
     * @param classId
     */
    @Override
    public void batchUpdate(Integer classId) {
        stuMapper.batchUpdate(classId);
    }
}
