package com.zong_zhaobin.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zong_zhaobin.myzhxy.pojo.Clazz;
import com.zong_zhaobin.myzhxy.service.ClazzService;
import com.zong_zhaobin.myzhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:26
 */
@Api(tags = "班级管理器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @ApiOperation("查询所有班级信息")
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzes = clazzService.getClazzs();
        return Result.ok(clazzes);
    }

    @ApiOperation("删除单个或多个Clazz信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(
        @ApiParam("要删除的所有clazz的id的JSON数组")   @RequestBody List<Integer> ids
    ){
        clazzService.removeByIds(ids);
        return Result.ok();

    }


    @ApiOperation("增加或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
           @ApiParam("JSON格式的班级信息") @RequestBody  Clazz clazz
    ){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }


    @ApiOperation("分页带条件查询班级信息")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzByOpr(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询页大小")@PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询的查询条件") Clazz clazz
    ){
        Page<Clazz> page =new Page<>(pageNo,pageSize);

        IPage<Clazz> iPage=clazzService.getClazzByOpr(page,clazz);

        return Result.ok(iPage);

    }
}
