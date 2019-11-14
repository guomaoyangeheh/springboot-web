package com.gmy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/11/13 16:08
 */
@Data
@TableName("tb_tabmenu")
public class TabMenu {

    @TableId(type = IdType.UUID)
    private String id;

    private String pid;
    private String title;
    private String href;
    private String icon;
    private String target;

}
