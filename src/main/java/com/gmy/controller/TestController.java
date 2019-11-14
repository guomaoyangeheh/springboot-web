package com.gmy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gmy.bean.MenuVo;
import com.gmy.config.exception.BusException;
import com.gmy.dao.TabMenuMapper;
import com.gmy.dao.UserMapper;
import com.gmy.entity.TabMenu;
import com.gmy.entity.User;
import com.gmy.utils.ResultBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo Mao Yang
 * @date 2019/9/24 15:07
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TabMenuMapper tabMenuMapper;

    @GetMapping("/getName")
    @ResponseBody
    public String getName(){
        return "gmy3";
    }

    @GetMapping("/getMsg")
    @ResponseBody
    public ResultBody getMsg(){
        if(1 ==1 ){
            throw new BusException("测试异常！");
        }

        return ResultBody.success();
    }

    @GetMapping("/getuUsers")
    @ResponseBody
    public List<User> getuUsers(){
        List<User> users = userMapper.selectList(null);

        return users;
    }

    @GetMapping("/getTabMenus")
    @ResponseBody
    public ResultBody getTabMenus(){
        QueryWrapper<TabMenu> qw = new QueryWrapper<>();
        qw.isNull("pid");
        //查找所有pid为空的menu
        List<TabMenu> tabMenus = tabMenuMapper.selectList(qw);
        List<MenuVo> menuVos = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(tabMenus)){//第一级的菜单
            for (TabMenu tabMenu : tabMenus) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(tabMenu,menuVo);
                //查询父id为当前菜单的子菜单
                QueryWrapper<TabMenu> qw2 = new QueryWrapper<>();
                qw2.eq("pid",tabMenu.getId());
                List<TabMenu> menuChilds = tabMenuMapper.selectList(qw2);
                List<MenuVo> child = new ArrayList<>();
                if(CollectionUtils.isNotEmpty(menuChilds)){
                    for (TabMenu menuChild : menuChilds) {
                        MenuVo childVo = new MenuVo();
                        BeanUtils.copyProperties(menuChild,childVo);
                        child.add(childVo);
                    }
                }
                menuVo.setChild(child);
                menuVos.add(menuVo);
            }
        }

        return ResultBody.success(menuVos);
    }



    /*@RequestMapping("/index")
    public String getIndex(){
        return "index";
    }*/
}
