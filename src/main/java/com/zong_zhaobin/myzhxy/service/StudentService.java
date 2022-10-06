package com.zong_zhaobin.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zong_zhaobin.myzhxy.pojo.LoginForm;
import com.zong_zhaobin.myzhxy.pojo.Student;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:19
 */
public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);
}
