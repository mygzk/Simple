package com.example.lib.clone;

/**
 * Created by guozhk on 2018/5/29.
 */

public class Student implements Cloneable {

    private int number;
    private Address address;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    protected Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (stu != null) {
            stu.address = (Address) address.clone();
        }

        return stu;
    }

    @Override
    public String toString() {
        String str = "number:" + number + "  adress:" + address.getAdd();
        return str;
    }
}
