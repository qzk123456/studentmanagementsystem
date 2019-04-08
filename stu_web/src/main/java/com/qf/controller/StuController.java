package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.common.ClassVo;
import com.qf.common.Result;
import com.qf.common.StudentVo;
import com.qf.entity.Student;
import com.qf.sevice.IStuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 *
 *
 */
@Controller
@RequestMapping("/stu")
public class StuController {

    @Reference
    private IStuService stuService;

    /**
     * 显示所有学生信息
     * @param map
     * @return
     */
    @RequestMapping("/show")
    public String queryAllStudent(ModelMap map){
        List<StudentVo> students = stuService.queryAllStudent();
        map.put("stus", students);
        return "stu_list";
    }

    /**
     * 跳转添加学生页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap map){
        List<ClassVo> classes = stuService.getClasses();
        map.put("classes", classes);
        return "stu_add";
    }

    /**
     * 添加学生信息,同时该班级的学生人数+1
     * @param student
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Student student){
        int count = stuService.addStudent(student);
        Result result = new Result();
        if (count>0) {
            result.setData("添加成功！");
        }else {
            result.setData("添加失败！");
        }
        return result;
    }

    /**
     * 回显学生信息
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toUpdate")
    public String queryOne(Integer id, ModelMap map){
        StudentVo studentVo = stuService.queryOneStudentById(id);
        List<ClassVo> classes = stuService.getClasses();
        map.put("classes", classes);
        map.put("stu", studentVo);
        return "stu_update";
    }

    /**
     * 修改学生信息,修改班级时，对应的班级人数+-1
     * @param student
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Student student) {
        int count = stuService.updateStudent(student);
        Result result = new Result();
        if (count>0) {
            result.setData("修改成功！");
        }else {
            result.setData("修改失败！");
        }
        return result;
    }

    /**
     * 删除学生信息,该班级学生人数-1
     * @param id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result del(Integer id){
        int count = stuService.deleteStudentById(id);
        Result result = new Result();
        if (count>0) {
            result.setData("删除成功！");
        }else {
            result.setData("删除失败！");
        }
        return result;
    }
}
