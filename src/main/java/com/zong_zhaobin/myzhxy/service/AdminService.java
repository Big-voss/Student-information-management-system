package com.zong_zhaobin.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zong_zhaobin.myzhxy.pojo.Admin;
import com.zong_zhaobin.myzhxy.pojo.LoginForm;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:02
 */
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);

    IPage<Admin> getAdminByOpr(Page<Admin> pageParam, String adminName);
}
