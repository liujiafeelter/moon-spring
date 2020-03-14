package com.moon.store.service;

import com.moon.store.dao.UserDao;
import com.moon.store.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    public int delById(int id){
        return userDao.delById(id);
    }

    public int update(Map<String, Object> map){ return userDao.update(map); }

    public int add(User user){ return userDao.add(user);  }

}
