package com.zong_zhaobin.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zong_zhaobin.myzhxy.pojo.Grade;

import java.util.List;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 22:18
 */
public interface GradeService extends IService<Grade> {
    IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName);

    List<Grade> getGrades();
}
