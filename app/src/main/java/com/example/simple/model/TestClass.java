package com.example.simple.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

/**
 * Created by guozhk on 17-1-11.
 */


public class TestClass {
    public static void main(String[] agrs){
        A a = new B();
        a.test();

    }




    static class A{
        public A(){
            print("a init");
        }
        public void test(){
            print("i am a");
        }
    }

    static class  B extends A{
        public B(){
            print("b init");
        }
        public void test(){
            print("i am b");
        }
    }


    public static void print(Object s){
        System.out.println(s);
    }
}


