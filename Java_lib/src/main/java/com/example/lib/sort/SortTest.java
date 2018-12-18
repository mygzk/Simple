package com.example.lib.sort;

/**
 * Created by guozhk on 2018/12/18.
 */

public class SortTest {

    static int[] data = {10, 2, 0, 1, 5, 23, 22};

    public static void main(String[] agrs) {
        ISort iSort;
//        iSort = new MaopaoSort();
//        iSort.sortData(data);

        iSort = new QuckSort();
        iSort.sortData(data);


    }


}
