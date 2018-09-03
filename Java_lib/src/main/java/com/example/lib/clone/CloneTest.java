package com.example.lib.clone;

/**
 * Created by guozhk on 2018/5/29.
 */

public class CloneTest {


    public static void main(String[] agrs) {
        Student stu1 = new Student();

        stu1.setNumber(12345);
        Address address = new Address();
        address.setAdd("stu1----");
        stu1.setAddress(address);

        Student stu2 = (Student) stu1.clone();
        stu2.setNumber(123);
        stu2.getAddress().setAdd("stu2----");
        System.out.println("stu1:" + stu1.toString());
        System.out.println("stu2:" + stu2.toString());


    }
}
