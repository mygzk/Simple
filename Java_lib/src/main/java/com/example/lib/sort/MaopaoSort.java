package com.example.lib.sort;

/**
 * Created by guozhk on 2018/12/18.
 * 冒泡排序
 */

public class MaopaoSort implements ISort {


    @Override
    public void sortData(int[] data) {

        for (int i = 0; i < data.length; i++) {

            for (int j = i + 1; j < data.length; j++) {
                int temp = data[i];
                int d = data[j];
                if (d < temp) {
                    data[i] = d;
                    data[j] = temp;
                }


            }


        }

        printArry(data);
    }

    @Override
    public void printArry(int[] data) {

        if (data == null) {
            return;
        }

        System.out.println("==maopao sort==");
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();


    }
}
