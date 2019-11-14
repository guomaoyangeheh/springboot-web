package com.gmy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gmy.dao.MenuMapper;
import com.gmy.dao.RoleMenuMapper;
import com.gmy.dao.UserMapper;
import com.gmy.dao.UserRoleMapper;
import com.gmy.entity.*;
import com.gmy.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo Mao Yang
 * @date 2019/9/29 13:17
 */
@Service
public class UserServiceImpl implements UserService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenus(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(user == null){
            return null;
        }
        //获取当前用户所有的角色
        LambdaQueryWrapper<UserRole> userRoleQw = Wrappers.<UserRole>lambdaQuery();
        userRoleQw.eq(UserRole::getUserId,user.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQw);
        LambdaQueryWrapper<RoleMenu> roleMenuQW = Wrappers.<RoleMenu>lambdaQuery();
        List<String> roleIds = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(userRoles)){
            for (UserRole userRole : userRoles) {
                roleIds.add(userRole.getRoleId());
            }
        }
        //根据角色id查到所有对应的菜单
        LambdaQueryWrapper<RoleMenu> qw = Wrappers.<RoleMenu>lambdaQuery();
        qw.in(RoleMenu::getRoleId, roleIds);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(qw);
        List<String> menuIds = new ArrayList<String>();
        for (RoleMenu roleMenu : roleMenus) {
            menuIds.add(roleMenu.getMenuId());
        }
        LambdaQueryWrapper<Menu> menuQw = Wrappers.<Menu>lambdaQuery();
        menuQw.in(Menu::getId, menuIds);
        List<Menu> menus = menuMapper.selectList(menuQw);
        menus.forEach(System.out::println);
        return null;
    }

    public User getUser(String userName){
        //根据用户名查询password
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("username",userName);
        User user = userMapper.selectOne(query);
        if(user != null){
            return user;
        }else{
            return null;
        }
    }

}
