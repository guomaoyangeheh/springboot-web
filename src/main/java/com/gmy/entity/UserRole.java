package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/29 13:12
 */

@Data
@TableName("tb_user_role")
public class UserRole {
    @TableId(type = IdType.UUID)
    private String id;

    private String userId;
    private String roleId;
}
