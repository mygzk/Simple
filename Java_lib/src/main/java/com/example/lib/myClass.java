package com.example.lib;

import com.example.lib.sort.InsertSort;

public class myClass {
static     int[] array = {3,1,5,7,2,4,9,6,10,8};
    public static void main(String[] args){
        InsertSort obj=new InsertSort();
        obj.insertSort(array);

        printArry(array);
    }


    private static void printArry(int[] data){
        if(data==null){
            return;
        }

        for (int i:data){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
