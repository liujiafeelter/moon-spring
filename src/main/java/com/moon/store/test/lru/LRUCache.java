package com.moon.store.test.lru;

import java.util.Hashtable;

/**
 * https://www.jianshu.com/p/ec1952b9d84a
 */
public class LRUCache {

    private Hashtable<String, DLinkedNode> cache = new Hashtable();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.setPre(null);
        tail = new DLinkedNode();
        tail.setPost(null);

        head.setPost(tail);
        tail.setPre(head);
    }


    public static class DLinkedNode {

        private String key;
        private DLinkedNode pre;
        private DLinkedNode post;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public DLinkedNode getPre() {
            return pre;
        }

        public void setPre(DLinkedNode pre) {
            this.pre = pre;
        }

        public DLinkedNode getPost() {
            return post;
        }

        public void setPost(DLinkedNode post) {
            this.post = post;
        }
    }
}
