package com.example.lib.sort;

/**
 * 希尔排序时效分析很难，关键码的比较次数与记录移动次数依赖于增量因子序列d的选取，
 * 特定情况下可以准确估算出关键码的比较次数和记录的移动次数。目前还没有人给出选取最好的增量因子序列的方法。
 * 增量因子序列可以有各种取法，有取奇数的，也有取质数的，
 * 但需要注意：增量因子中除1 外没有公因子，且最后一个增量因子必须为1。
 *
 * 希尔排序方法是一个不稳定的排序方法。
 * Created by guozhk on 2018/3/14.
 */

public class ShellSort implements ISort{
    @Override
    public void sortData(int[] data) {
        int dk = data.length/2;
        while( dk >= 1  ){
            ShellInsertSort(data, dk);
            dk = dk/2;
        }
    }


    private void ShellInsertSort(int[] a, int dk) {//类似插入排序，只是插入排序增量是1，这里增量是dk,把1换成dk就可以了
        for(int i=dk;i<a.length;i++){
            if(a[i]<a[i-dk]){
                int j;
                int x=a[i];//x为待插入元素
                a[i]=a[i-dk];
                for(j=i-dk;  j>=0 && x<a[j];j=j-dk){//通过循环，逐个后移一位找到要插入的位置。
                    a[j+dk]=a[j];
                }
                a[j+dk]=x;//插入
            }

        }

    }
}
