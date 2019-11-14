package com.gmy.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gmy.dao.UserMapper;
import com.gmy.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Guo Mao Yang
 * @date 2019/9/26 11:27
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权方法=============================================");
        return null;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入认证方法=============================================");
        String userName = (String) authenticationToken.getPrincipal();
        User user = getPasswordByUserName(userName);
        if(user == null){
            throw  new AccountException("账号不存在！");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("gmy"));//设置加盐
        return authenticationInfo;
    }
    private User getPasswordByUserName(String userName){
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
