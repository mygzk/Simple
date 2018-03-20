package com.example.lib;

import com.example.lib.sort.BubbleSort;
import com.example.lib.sort.ISort;
import com.example.lib.sort.InsertSort;
import com.example.lib.sort.ShellSort;
import com.example.lib.sort.SimpleSelectSort;

import java.text.DecimalFormat;


public class myClass {
static     int[] array = {3,1,5,7,2,4,9,6,10,8};
    public static void main(String[] args) {


//        InsertSort obj=new InsertSort();
//        obj.insertSort(array);
//
//        printArry(array);

        // ShellSort shellSort = new ShellSort();
        // shellSort.shellSort(array);

        //toSort(new BubbleSort());

        System.out.println(getFormatFee("1.1"));
        System.out.println(getFormatFee("1"));
        System.out.println(getFormatFee("1"));
        System.out.println(getFormatFee("0.0"));
        System.out.println(getFormatFee("0.1"));
        System.out.println(getFormatFee("0.1000"));
        System.out.println(getFormatFee("0.0001"));
    }


    private static String getFormatFee(String str) {
        if (str == null) {
            return "0";
        }
        if (str.equals("")) {
            return "0";
        }
        try {
            DecimalFormat df = new DecimalFormat(("##,##0.00"));
            return df.format(Double.parseDouble(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

    }

    private static void toSort(ISort sort){
        sort.sortData(array);
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
