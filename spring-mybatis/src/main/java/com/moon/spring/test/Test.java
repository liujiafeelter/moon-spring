package com.moon.spring.test;

import com.moon.spring.test.dao.UserDao;
import com.moon.spring.test.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean(UserDao.class);
        User user = userDao.getUser();
        System.out.println(user.getName());
    }



}
