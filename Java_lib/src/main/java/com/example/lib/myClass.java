package com.example.lib;

import com.example.lib.sort.BubbleSort;
import com.example.lib.sort.ISort;
import com.example.lib.sort.InsertSort;
import com.example.lib.sort.ShellSort;
import com.example.lib.sort.SimpleSelectSort;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class myClass {
static     int[] array = {3,1,5,7,2,4,9,6,10,8};
    public static void main(String[] args) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(getDaysOfMonth(sdf.parse("2015-02-2")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(getFormatFee("1.1"));
        System.out.println(getFormatFee("1"));
        System.out.println(getFormatFee("1"));
        System.out.println(getFormatFee("0.0"));
        System.out.println(getFormatFee("0.1"));
        System.out.println(getFormatFee("0.1000"));
        System.out.println(getFormatFee("0.0001"));
    }



    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
