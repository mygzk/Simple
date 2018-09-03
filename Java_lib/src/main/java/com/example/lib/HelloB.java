package com.example.lib;

/**
 * Created by guozhk on 2018/5/10.
 */

public class HelloB extends HelloA {

    public HelloB(){
        System.out.println("c-b");
    }

    static {
        System.out.println("s-b");
    }

    {
        System.out.println("s 000-b");

    }

    public static void main(String[] ages){
        HelloA a =    new HelloB();
        System.out.println("------0000-----");
        HelloA a1=  new HelloA();
        System.out.println("------111-----");
        HelloA a2=  new HelloB();







//        System.out.println("55"+2);
//
//        HelloB b= new HelloB();
//        b.change(b.str,b.ch);
//        System.out.print(b.str+" and ");
//        System.out.print(b.ch);


    }



    public void change(String s,char ch[]){
        str="change ok";
        ch[0]='s';

    }





    String str = new String("good");

    char[] ch = {'a','b','c'};




}
