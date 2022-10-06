package com.zong_zhaobin.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zong_zhaobin.myzhxy.pojo.Grade;
import com.zong_zhaobin.myzhxy.service.GradeService;
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
@Api(tags = "年纪控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @ApiOperation("获取全部年纪")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();

        return Result.ok(grades);
    }


    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(
          @ApiParam("要删除的所有的grade的id的json集合") @RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("新增或修改grade,有id属性是修改,没有是增加")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(
       @ApiParam("Json的grade对象") @RequestBody  Grade grade
    ){
        gradeService.saveOrUpdate(grade);

        return Result.ok();
    }

    @ApiOperation("根据年纪名称模糊查询,带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询的页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("分页查询模糊匹配的名称") String gradeName
    ){
        Page<Grade> page = new Page<>(pageNo,pageSize);

        IPage<Grade> pageRs = gradeService.getGradeByOpr(page,gradeName);

        return Result.ok(pageRs);

    }
}
