package com.moon.store.test.lru;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 1、LinkedList 双向链表实现
 * 链接尾部是最新的元素
 * put、get 时间复杂度:O(n)
 */
public class LRU$LinkedList {

    LinkedList<String> cache = new LinkedList();
    int capacity = 3;

    public LinkedList getCache() {
        Iterator it = cache.descendingIterator();
        while (it.hasNext()){
            System.out.print(it.next());
        }
        System.out.println("============================");
        return cache;
    }

    /**
     * --1、先遍历链接，有则删除
     * --2、判断容量，满了则删除链表头部一个元素
     * --3、添加到链表尾部
     */
    public void put(String key){
        Iterator<String> it = cache.iterator();
        while(it.hasNext()){
            String k = it.next();
            if(k.equals(key)){
                it.remove();
                break;
            }
        }

        if(capacity == cache.size()){
            cache.removeFirst();
        }

        cache.addLast(key);
    }

    /**
     * --1、先遍历链接，有则删除，然后添加到链表尾部
     * @param key
     */
    public void get(String key){
        Iterator<String> it = cache.iterator();
        while(it.hasNext()){
            String k = it.next();
            if(k.equals(key)){
                it.remove();
                cache.addLast(key);
                break;
            }
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        //a
        //b
        //c
        //d 满了，移除最早的元素(dcb)
        //c 访问了，提到最近处(cdb)
        LRU$LinkedList cache = new LRU$LinkedList();
        cache.put("a");cache.getCache();
        cache.put("b");cache.getCache();
        cache.put("c");cache.getCache();
        cache.put("d");cache.getCache();
        cache.get("c");cache.getCache();
    }

}
