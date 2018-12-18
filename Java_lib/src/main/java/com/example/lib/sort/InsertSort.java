package com.example.lib.sort;

/**
 * 直接插入排序
 * 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
 *要点：设立哨兵，作为临时存储和判断数组边界之用。
 *
 *所以插入排序是稳定的。
 *
 *时间复杂度：O（n^2）.
 * Created by guozhk on 2018/3/13.
 */

public class InsertSort implements ISort{
    @Override
    public void sortData(int[] a) {
        for(int i=1;i<a.length;i++){//从头部第一个当做已经排好序的，把后面的一个一个的插到已经排好的列表中去。
            int j;
            int x=a[i];//x为待插入元素
            System.out.println(" befor j:="+i);
            for( j=i;  j>0 && x<a[j-1];j--){//通过循环，逐个后移一位找到要插入的位置。
                a[j]=a[j-1];

                printArry(a);
            }
            System.out.println(" after j:="+j);


            a[j]=x;//插入
            printArry(a);
            System.out.println("---------------");
        }
    }



    @Override
    public void printArry(int[] data){
        if(data==null){
            return;
        }
        System.out.print("InsertSort :");
        for (int i:data){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
