package com.moon.store.dao;

import com.moon.store.domain.User;

import java.util.Map;

public interface UserDao {

       public int add(User user);//返回新增的个数

       public int delById(int id);//返回删除的个数

       public int update(Map<String, Object> map);//返回修改的个数

       public User getById(int id);

}
