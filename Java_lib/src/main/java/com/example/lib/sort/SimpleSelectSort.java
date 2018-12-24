package com.example.lib.sort;

/**
 * 简单选择排序
 * <p>
 * Created by guozhk on 2018/3/14.
 */

public class SimpleSelectSort implements ISort {

    @Override
    public void sortData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int k = i;

            for (int j = i + 1; j < data.length; j++) {
                if (data[k] > data[j]) {
                    k = j;
                }
            }

            int l = data[i];
            data[i] = data[k];
            data[k] = l;

        }
    }


    @Override
    public void printArry(int[] data) {

    }
}
