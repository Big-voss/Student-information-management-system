package com.zong_zhaobin.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zong_zhaobin.myzhxy.pojo.Student;
import com.zong_zhaobin.myzhxy.service.StudentService;
import com.zong_zhaobin.myzhxy.util.MD5;
import com.zong_zhaobin.myzhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:26
 */
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("删除单个或多个学生信息")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(
        @ApiParam("要删除学生编号的JSON数组")  @RequestBody List<Integer> ids
    ){
        studentService.removeByIds(ids);
        return Result.ok();
    }


    @ApiOperation("保存或者修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
         @ApiParam("要保存或修改的学生JSON")  @RequestBody Student student
    ){
        Integer id = student.getId();
        if (null == id || 0 == id) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("分页带条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(
        @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
        @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
        @ApiParam("查询的条件") Student student
    ){
        Page page = new Page(pageNo,pageSize);
        Page<Student> pageParam = new Page(pageNo,pageSize);
        IPage<Student> studentPage= studentService.getStudentByOpr(pageParam,student);
        return Result.ok(studentPage);
    }
}
