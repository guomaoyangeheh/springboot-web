package com.gmy.config;

import com.gmy.dao.MenuMapper;
import com.gmy.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置页面路径映射
 * @author Guo Mao Yang
 * @date 2019/9/25 13:54
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //配置页面跳转
        List<Menu> menus = menuMapper.selectList(null);
        for (Menu menu : menus) {
            registry.addViewController(menu.getPath()).setViewName(menu.getUrl());
        }
    }
}
