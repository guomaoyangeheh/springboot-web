package com.gmy.springbootweb;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class SpringbootWebApplicationTests {

    SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void before(){
        realm.addAccount("gmy","123456");
    }


    @Test
    public void contextLoads() {
        //创建一个SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //创建主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("gmy", "123456");
        subject.login(token);
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证成功：" + authenticated );

    }
    @Test
    public void encodeText() {
        //md5加密
        Md5Hash md5Hash = new Md5Hash("123456","gmy");//加盐
        System.out.println(md5Hash.toString());
    }

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("2018-10-11 13:00:03");
        list.add("2018-10-11 13:00:02");
        list.add("2018-10-11 13:00:01");
        List<String> newList = list.stream().sorted((x, y) -> {
            int i = x.compareTo(y);
            return -i;
        }).collect(Collectors.toList());
        newList.forEach(System.out::println);

    }

    @Test
    public void test2() {
        LinkedList<String> link = new LinkedList<>();
        link.add("A");
        link.add("B");
        link.add("C");
        link.add("D");
        link.add("E");
        for (int i = 0; i < 5; i++) {
            System.out.println(link.pop());
        }

    }

}
