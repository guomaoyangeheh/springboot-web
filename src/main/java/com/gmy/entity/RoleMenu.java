package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/29 13:14
 */

@Data
@TableName("tb_role_menu")
public class RoleMenu {
    @TableId(type = IdType.UUID)
    private String id;
    private String roleId;
    private String menuId;
}
