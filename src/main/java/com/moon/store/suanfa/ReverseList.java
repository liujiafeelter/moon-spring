package com.moon.store.suanfa;

/**
 * 利用递归
 * 从后往前更改指向
 */
public class ReverseList {

    //1-->2-->3-->4-->5
    //5-->4-->null
    public ListNode ReverseList(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode next = head.next;//next是5
        ListNode newHead = ReverseList(next);
        next.next = head;//5的下一个变为4
        head.next = null;//4的下一个变为空
        return newHead;
    }

}

class ListNode{
    int data;
    ListNode next;
    public ListNode(int data, ListNode next){
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
