package com.gmy.service;

import com.gmy.entity.Menu;
import com.gmy.entity.User;

import java.util.List;

/**
 * @author Guo Mao Yang
 * @date 2019/9/29 13:16
 */
public interface UserService {

    /**
     * 获取当前用户菜单
     * @return
     */
    List<Menu> getMenus();
    User getUser(String username);
}
