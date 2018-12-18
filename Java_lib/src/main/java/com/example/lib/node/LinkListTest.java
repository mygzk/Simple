package com.example.lib.node;

/**
 * Created by guozhk on 2018/12/18.
 */

public class LinkListTest {

    public Node head;

    public Node current;


    //方法：向链表中添加数据
    public void add(int data) {
        //判断链表为空的时候
        if (head == null) {//如果头结点为空，说明这个链表还没有创建，那就把新的结点赋给头结点
            head = new Node(data);
            current = head;
        } else {
            //创建新的结点，放在当前节点的后面（把新的结点合链表进行关联）
            current.next = new Node(data);
            //把链表的当前索引向后移动一位
            current = current.next;   //此步操作完成之后，current结点指向新添加的那个结点
        }
    }

    //方法：遍历链表（打印输出链表。方法的参数表示从节点node开始进行遍历
    public void print(Node node) {
        if (node == null) {
            return;
        }

        current = node;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }


}
