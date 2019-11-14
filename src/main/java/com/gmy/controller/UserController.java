package com.gmy.controller;

import com.gmy.bean.UrlBean;
import com.gmy.entity.Menu;
import com.gmy.entity.User;
import com.gmy.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Guo Mao Yang
 * @date 2019/9/26 13:53
 */
@Controller
public class UserController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/userLogin")
    @ResponseBody
    public UrlBean userLogin(User user, HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try{
            //进行认证
            subject.login(token);
            //认证成功设置user
            user = userService.getUser(user.getUsername());
            subject.getSession().setAttribute("user",user);

            //request.getSession().setAttribute("user",user);
            return new UrlBean(200,"/index","登录成功！");

        }catch (Exception e){
            logger.info("认证失败，用户名或密码错误！");
            logger.error(e.getMessage(),e);
            return new UrlBean(0,"","用户名或密码错误！");
        }
    }
    @GetMapping("/getMenus")
    @ResponseBody
    public List<Menu> getMenus(HttpServletRequest request){
        List<Menu> menus = userService.getMenus();
        return menus;
    }

}
