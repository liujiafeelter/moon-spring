package com.moon.store.test.aba;

import org.springframework.stereotype.Service;

@Service
public class B {

    private A a;

    public B(){
        System.out.println("B 构造器:" + a);
    }


}
