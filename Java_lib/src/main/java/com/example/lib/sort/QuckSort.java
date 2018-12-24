package com.example.lib.sort;

/**
 * Created by guozhk on 2018/12/18.
 * <p>
 * <p>
 * 快速排序
 */

public class QuckSort implements ISort {


    @Override
    public void sortData(int[] data) {
        sort(data, 0, data.length - 1);

        printArry(data);
    }


    private void sort(int[] data, int low, int hight) {
        if (low < hight) {
            int model = getMiddle(data, low, hight);

            sort(data, low, model);
            sort(data, model + 1, hight);

        }
    }


    private int getMiddle(int[] data, int low, int hight) {
        int temp = data[low];

        while (low < hight) {
            while (low < hight && data[hight] > temp) {
                hight--;
            }
            data[low] = data[hight];

            printArry(data);
            while (low < hight && data[low] < temp) {
                low++;
            }
            data[hight] = data[low];
            printArry(data);
        }
        data[low] = temp;

        return low;

    }


    @Override
    public void printArry(int[] data) {

        if (data == null) {
            return;
        }
        System.out.println("==quick sort==");
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
