package com.example.lib.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * create by guozhk on 2019/4/13 10:28
 **/
public class FileTest {

    public static void main(String[] agrs) {

        System.out.println("0-0-0-");
        System.out.println(FileTest.class.getResource("").getPath());

        readClass(new TempClass());
    }


    private static void readClass(Object obj) {
        String path = FileTest.class.getResource("").getPath();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            // 对于每个属性，获取属性名
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o;
                try {
                    o = fields[i].get(obj);
                    System.err.println(varName + " : " + o);

                   // String printStr = "public static final String "+ varName +" = BASE_SERVER_URL +\""+ o+"\";";
                    String printStr = "configs.put("+varName+","+"IPConstanse."+varName+");";

                    toWriteFile(path, printStr);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }


        }
    }


    private static void toWriteFile(String path, String content) {
        // String path = FileTest.class.getResource("").getPath();

        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File file = new File( "Text.txt");
            if (!file.exists())
                file.createNewFile();

            System.out.println(file.getAbsolutePath());
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMe(){

        for (int i=0;i<48;i++){

        }

    }

    private static void toWriteFile1( String content) {
        // String path = FileTest.class.getResource("").getPath();

        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File file = new File( "Meet.txt");
            if (!file.exists())
                file.createNewFile();

            System.out.println(file.getAbsolutePath());
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
