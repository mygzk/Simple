package com.example.lib.sort;

/**
 * Created by guozhk on 2018/3/14.
 */

public class BubbleSort implements ISort {
    @Override
    public void sortData(int[] data) {

        for (int i=0;i<data.length;i++){
            int tmp;
            for (int j=0;j<data.length-1-i;j++){
                if(data[j]>data[j+1]){
                    tmp=data[j];
                    data[j]=data[j+1];
                    data[j+1]=tmp;
                }
            }
        }

    }


    @Override
    public void printArry(int[] data) {

    }
}
