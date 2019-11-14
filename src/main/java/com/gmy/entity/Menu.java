package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/25 14:21
 */
@Data
@TableName("tb_menu")
public class Menu {
    @TableId(type = IdType.UUID)
    private String id;
    private String path;
    private String url;
    @TableField("menu_name")
    private String menuName;
}
