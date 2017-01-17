package com.example.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static android.R.attr.path;

/**
 * Created by guozhk on 17-1-11.
 */

public class TestClass {


    public static void replaceTxtByStr(String path) {
        String temp = "";
        boolean startWrite = false;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            // 保存该行前面的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                if (temp.contains("四百四十四")) {
                    System.out.println("temp：" + temp);
                    startWrite = true;
                }


                if (startWrite) {
                    buf = buf.append(temp);
                    buf = buf.append("/n");
                }
            }

            // 将内容插入
            //  buf = buf.append(replaceStr);

            // 保存该行后面的内容
            while ((temp = br.readLine()) != null) {
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] agrs) {
        String path = "app/src/main/java/com/example/simple/rendaozhizun.txt";
        File file = new File(path);
        if (file.exists()) {
            replaceTxtByStr(path);
        } else {
            System.out.println("file is not exits");
        }
        System.out.println(" finish");
    }
}
