package com.moon.store.test.aba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A {

    @Autowired
    private B b;

    public A(){
        System.out.println("A 构造器:" + b);
    }

}
