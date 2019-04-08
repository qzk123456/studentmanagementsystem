package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.common.Result;
import com.qf.entity.Class;
import com.qf.sevice.IClassService;
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
@RequestMapping("/class")
public class ClassController {

    @Reference
    private IClassService classService;

    /**
     * 查询所有班级信息
     * @param map
     * @return
     */
    @RequestMapping("/show")
    public String queryAll(ModelMap map){
        List<Class> classes = classService.queryAllClass();
        map.put("classes", classes);
        return "class_list";
    }

    /**
     * 跳转添加班级页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "class_add";
    }

    /**
     * 添加班级信息
     * @param c
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Class c){
        int count = classService.addClass(c);
        Result result = new Result();
        if (count>0) {
            result.setData("添加成功！");
        }else {
            result.setData("添加失败！");
        }
        return result;
    }

    /**
     * 回显班级信息
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, ModelMap map){
        Class c = classService.queryOneClassById(id);
        map.put("c", c);
        return "class_update";
    }

    /**
     * 修改班级信息
     * @param c
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Class c){
        int count = classService.updateClass(c);
        Result result = new Result();
        if (count>0) {
            result.setData("修改成功！");
        }else {
            result.setData("修改失败！");
        }
        return result;
    }

    /**
     * 删除班级信息，对应的学生所在班级变为空
     * @param id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result del(Integer id){
        int count = classService.deleteClassById(id);
        Result result = new Result();
        if (count>0) {
            result.setData("删除成功！");
        }else {
            result.setData("删除失败！");
        }
        return result;
    }
}
