package com.gmy.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Guo Mao Yang
 * @date 2019/11/14 9:15
 */
@Data
public class MenuVo {
    private String title;
    private String href;
    private String icon;
    private String target;
    List<MenuVo> child;
}
