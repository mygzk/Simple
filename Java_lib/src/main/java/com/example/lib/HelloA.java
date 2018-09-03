package com.example.lib;

/**
 * Created by guozhk on 2018/5/10.
 */

public class HelloA {
    public HelloA(){
        System.out.println("c-a");
    }

    {
        System.out.println("s 000-a");

    }
    static {
        System.out.println("s-a");
    }

}
