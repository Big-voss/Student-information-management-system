package com.zong_zhaobin.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zong_zhaobin.myzhxy.pojo.LoginForm;
import com.zong_zhaobin.myzhxy.pojo.Teacher;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:19
 */
public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(Long userId);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> paraParam, Teacher teacher);
}
