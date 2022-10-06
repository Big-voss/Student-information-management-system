package com.zong_zhaobin.myzhxy.pojo;

import lombok.Data;

/**
 * @author zzb
 * @version V1.0
 * @creat 2022 0826 21:49
 */
@Data
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
