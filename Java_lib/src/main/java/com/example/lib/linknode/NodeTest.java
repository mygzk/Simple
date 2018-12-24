package com.example.lib.linknode;

/**
 * Created by guozhk on 2018/12/18.
 * 链表测试类
 */

public class NodeTest {

    private LinkListTest mLinkListTest;
    private NodeUtil mNodeUtils;

    public static void main(String[] agrs) {

        NodeTest nodeTest = new NodeTest();
        nodeTest.initNode();

        nodeTest.getNodeLength();
        nodeTest.getCounDonwNode(1);
//        nodeTest.getCounDonwNode(2);
//        nodeTest.getCounDonwNode(0);
//        nodeTest.getCounDonwNode(11);
//        nodeTest.getCounDonwNode(12);
//        nodeTest.getCounDonwNode(-1);
        nodeTest.getModdleNode();

        nodeTest.mergerNode();


    }


    private void initNode() {
        mLinkListTest = new LinkListTest();


        for (int i = 0; i < 9; i++) {
            mLinkListTest.add(i);
        }

        mLinkListTest.print(mLinkListTest.head);

        mNodeUtils = new NodeUtil();
    }


    private void getNodeLength() {
        int l = mNodeUtils.getLength(mLinkListTest.head);

        print("node length：" + l);
    }


    private void getCounDonwNode(int pos) {
        Node node = mNodeUtils.getNodeByDoenPos(mLinkListTest.head, pos);
        if (node != null) {
            print("node ：" + node.data);
        } else {
            print("倒数第" + pos + "个node is null");
        }
    }

    private void getModdleNode() {
        Node node = mNodeUtils.getMiddleNode(mLinkListTest.head);
        if (node != null) {
            print("getModdleNode ：" + node.data);
        } else {
            print("getModdleNodenode is null");
        }
    }


    private void mergerNode() {
        LinkListTest mLinkListTest1 = new LinkListTest();


        for (int i = 4; i < 14; i++) {
            mLinkListTest1.add(i);
        }
        mLinkListTest1.print(mLinkListTest1.head);


        Node node = mNodeUtils.mergeSingle(mLinkListTest.head, mLinkListTest1.head);

        if (node != null) {
            print("mergerNode ：" + node.data);

            mLinkListTest1.print(node);
        } else {
            print("mergerNode is null");
        }

    }


    private void print(String s) {
        System.out.println(s);
    }
}
