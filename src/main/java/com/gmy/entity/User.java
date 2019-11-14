package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/24 15:34
 */
@Data
@TableName("tb_user")
public class User {
    @TableId(type = IdType.UUID)
    private String id;
    private String username;
    private String password;
    private String email;
    private Integer age;
}
