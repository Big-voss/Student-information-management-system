package com.zong_zhaobin.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zong_zhaobin.myzhxy.pojo.Teacher;
import com.zong_zhaobin.myzhxy.service.TeacherService;
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
@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("分页获取教师信息,带搜索条件")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo ,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize ,
            @ApiParam("查询条件") Teacher teacher
    ){
        Page<Teacher> paraParam =new Page<>(pageNo,pageSize);

        IPage<Teacher> page = teacherService.getTeachersByOpr(paraParam,teacher);

        return Result.ok(page);
    }

    @ApiOperation("添加或者修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(
         @ApiParam("要保存或者修改的JSON格式的Teacher对象") @RequestBody  Teacher teacher
            ){
        if (teacher.getId() == null || teacher.getId() == 0){
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
            teacherService.saveOrUpdate(teacher);
            return Result.ok();
    }

    @ApiOperation("删除单个或多个教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(
          @ApiParam("要删除的教师信息id的JSON集合") @RequestBody List<Integer> ids
    ){
        teacherService.removeByIds(ids);
        return Result.ok();
    }

}
