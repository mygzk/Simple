package com.example.simple.syn;

import java.util.Objects;
import java.util.regex.Matcher;

/**
 * Created by guozhk on 2017/8/11.
 */

public class SynTest {
    public static void main(String[] agrs){
        //sysnRun1();

       // sysnRun2();
       // sysnRun3();


        getIntSum(-2);
        getIntSum(-28);
        getIntSum(989797);
        getIntSum(122);
    }

    /**
     * 同步代码块
     */

    public static void sysnRun1(){
        SyncThread syncThread = new SyncThread();
        Thread thread1= new Thread(syncThread,"thread1");
        Thread thread2= new Thread(syncThread,"thread2");
        thread1.start();
        thread2.start();
    }
    public static void sysnRun2(){
        SyncThread syncThread = new SyncThread();
        SyncThread syncThread1 = new SyncThread();
        Thread thread1= new Thread(syncThread,"2-thread1");
        Thread thread2= new Thread(syncThread1,"2-thread2");
        thread1.start();
        thread2.start();
    }

    public static void sysnRun3() {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "A");
        Thread thread2 = new Thread(syncThread, "B");
        thread1.start();
        thread2.start();
    }


    private static void print(Object object){
        System.out.println(object.toString());
    }

    public static int getIntSum(int num){
        int abNum = Math.abs(num);
        if(abNum<10){
            return abNum;
        }
        char[] chars = String.valueOf(abNum).toCharArray();
        int sum=0;
        for (int i=0;i<chars.length;i++){
            int  intNum = Integer.parseInt(String.valueOf(chars[i]));
            sum = sum+intNum;
        }

        return sum;
    }
}
