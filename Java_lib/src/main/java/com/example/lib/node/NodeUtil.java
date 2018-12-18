package com.example.lib.node;

/**
 * Created by guozhk on 2018/12/18.
 */

public class NodeUtil {


    /**
     * 求链表的长度
     *
     * @param node node
     * @return l
     */
    public int getLength(Node node) {
        if (node == null) {
            return 0;
        }

        int l = 0;
        Node current = node;

        while (current != null) {
            l++;
            current = current.next;
        }
        return l;
    }

    /**
     * 获取倒数第几个节点
     *
     * @param node node
     * @param k    倒数位置
     * @return node
     */

    public Node getNodeByDoenPos(Node node, int k) {
        if (node == null) {
            return null;
        }

        if (k <= 0) {
            return null;
        }
//        int legnth = getLength(node);
//        if (k >= legnth) { //超出节点个数
//            return null;
//        }

        Node currentNode = node;
        Node posNode = node;

        //向后移动pos 位置
        for (int i = 0; i < k; i++) {
            posNode = posNode.next;
            if (posNode == null) { //超出节点个数
                return null;
            }
        }

        while (posNode != null) {
            posNode = posNode.next;
            currentNode = currentNode.next;
        }


        return currentNode;
    }


    /**
     * 求中间节点
     * 长度为偶数时候 n/2+1
     */
    public Node getMiddleNode(Node node) {
        if (node == null) {
            return null;
        }
        Node firstNode = node;
        Node secondNode = node;

        while (secondNode != null && secondNode.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;

        }
        return firstNode;
    }


    /**
     * 合并两个有序单链表
     *
     * @param node1 node
     * @param node2 node
     * @return node
     */
    public Node mergeSingle(Node node1, Node node2) {

        if (node1 == null && node2 == null) {
            return null;
        }

        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        Node head;
        Node current;

        if (node1.data < node2.data) {
            head = node1;
            current = node1;
            node1 = node1.next;
        } else {
            head = node2;
            current = node2;
            node1 = node2.next;
        }
        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                current.next = node1;  //新链表中，current指针的下一个结点对应较小的那个数据
                current = current.next; //current指针下移
                node1 = node1.next;
            } else {
                current.next = node2;
                current = current.next;
                node2 = node2.next;
            }
           // System.out.println("while -->node1:"+node1.data+  "  node2:"+node2.data);
        }


        //合并剩余的元素
        if (node1 != null) { //说明链表2遍历完了，是空的
            current.next = node1;
        }

        if (node2 != null) { //说明链表1遍历完了，是空的
            current.next = node2;
        }



        return head;
    }



    private Node reversetNode(Node node){
        if(node==null){
            return null;
        }

        if(node.next==null){
            return node;
        }

        Node current = node;
        Node next = null; //定义当前结点的下一个结点
        Node reverseHead = null;  //反转后新链表的表头


        while (current != null) {
            next = current.next;  //暂时保存住当前结点的下一个结点，因为下一次要用

            current.next = reverseHead; //将current的下一个结点指向新链表的头结点
            reverseHead = current;

            current = next;   // 操作结束后，current节点后移
        }

        return reverseHead;
    }





    public void printNode(Node node) {


        if (node == null) {
            return;
        }
        System.out.print("node :");
        Node  current = node;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }
}
