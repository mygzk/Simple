package com.example.lib.sort;

/**
 * Created by guozhk on 2018/3/13.
 */

public class InsertSort {

    public void insertSort(int[] a) {
        for(int i=1;i<a.length;i++){//从头部第一个当做已经排好序的，把后面的一个一个的插到已经排好的列表中去。
            int j;
            int x=a[i];//x为待插入元素
            for( j=i;  j>0 && x<a[j-1];j--){//通过循环，逐个后移一位找到要插入的位置。
                a[j]=a[j-1];
            }
            a[j]=x;//插入
        }

    }
}
