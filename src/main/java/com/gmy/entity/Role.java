package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/29 11:09
 */
@Data
@TableName("tb_role")
public class Role {
    @TableId(type = IdType.UUID)
    private String id;
    @TableField("role_name")
    private String roleName;
    @TableField("role_caption")
    private String roleCaption;
}
