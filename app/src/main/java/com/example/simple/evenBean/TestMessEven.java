package com.example.simple.evenBean;

/**
 * Created by guozhk on 2017/12/14.
 */

public class TestMessEven {

    private int what;
    private String msg;


    public TestMessEven() {

    }

    public TestMessEven(int what) {
        this.what = what;
    }

    public TestMessEven(String msg) {
        this.msg = msg;
    }

    public TestMessEven(int what, String msg) {
        this.what = what;
        this.msg = msg;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TestMessEven{" +
                "what=" + what +
                ", msg='" + msg + '\'' +
                '}';
    }
}
