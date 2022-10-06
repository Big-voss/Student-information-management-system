package com.zong_zhaobin.myzhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zong_zhaobin.myzhxy.pojo.Admin;
import com.zong_zhaobin.myzhxy.service.AdminService;
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
@Api(tags = "管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("分页带条件查询管理员信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("管理员名字") String adminName
    ){
        Page<Admin> pageParam = new Page<Admin>(pageNo,pageSize);

       IPage<Admin> iPage = adminService.getAdminByOpr(pageParam,adminName);
       return Result.ok(iPage);
    }


    @ApiOperation("增加或修改管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(
         @ApiParam("JSON格式的的Admin对象")   @RequestBody Admin admin
    ){
        Integer id = admin.getId();
        if (id == null || 0 == id) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @ApiOperation("删除单个或多个管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(
       @ApiParam("要删除的管理员的多个ID的JSON集合")  @RequestBody   List<Integer> ids
    ){
        adminService.removeByIds(ids);

        return Result.ok();
    }
}
