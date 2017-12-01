package com.example.simple.syn;

/**
 * Created by guozhk on 2017/8/11.
 */

public class SyncThread implements Runnable {

    private  int mCount;

    public SyncThread() {
        mCount=0;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("A")) {
            addCount();
        } else if (threadName.equals("B")) {
            printCount();
        }else {
            addCount();
        }
    }

    public int getCount(){
        return mCount;
    }

    public  void addCount(){
        System.out.println(Thread.currentThread().getName()+"000000:"+(mCount++));
        synchronized (this){
            for (int i=0;i<5;i++){
                try {
                    System.out.println(Thread.currentThread().getName()+":"+(mCount++));
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCount(){
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + " mCount:" + mCount);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
