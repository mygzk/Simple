package com.example.lib.linknode;

import java.util.Stack;

/**
 * https://www.cnblogs.com/smyhvae/p/4782595.html
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


    /**
     * 单链表反转
     *
     * @param node node
     * @return node
     */
    public Node reversetNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.next == null) {
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


    /**
     * 从尾部 -->到头部打印 node
     *
     * @param node node
     */
    public void printeReserveNode(Node node) {
        if (node == null) {
            return;
        }


        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        System.out.println("------打印  start------");
        while (stack.size() > 0) {
            System.out.print(stack.pop().data);
        }
        System.out.println("------打印 end ------");
    }


    /**
     * 是否有环
     * 这里也是用到两个指针，如果一个链表有环，那么用一个指针去遍历，是永远走不到头的。
     * <p>
     * 因此，我们用两个指针去遍历：first指针每次走一步，second指针每次走两步，如果first指针和second指针相遇，说明有环。时间复杂度为O (n)。
     *
     * @param node node
     * @return boolean
     */
    public boolean hasCycle(Node node) {

        if (node == null) {
            return false;
        }

        Node first = node;
        Node second = node;
        while (second != null) {
            first = first.next;
            second = second.next;
            if (second != null) {
                second = second.next;
                if (first == second) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取环节点node
     *
     * @return node
     */
    public Node getCycleNode(Node node) {
        if (node == null) {
            return null;
        }

        Node first = node;
        Node second = node;
        while (second != null) {
            first = first.next;
            second = second.next;
            if (second != null) {
                second = second.next;
                if (first == second) {
                    return second;
                }
            }
        }

        return null;
    }


    /**
     * 获取环长度
     *
     * @param node node
     * @return int
     */
    public int getCycleLength(Node node) {

        if (node == null) {
            return 0;
        }


        Node cylcle = getCycleNode(node);
        if (cylcle == null) {
            return 0;
        }

        int length = 0;
        Node current = cylcle;

        while (current != null) {
            current = current.next;
            length++;

            if (current == cylcle) {
                return length;
            }
        }


        return 0;
    }


    /**
     * 打印节点
     *
     * @param node node
     */
    public void printNode(Node node) {
        if (node == null) {
            return;
        }
        System.out.print("node :");
        Node current = node;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }
}
