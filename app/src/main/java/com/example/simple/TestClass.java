package com.example.simple;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
            temp = br.readLine();

            // 保存该行前面的内容
            for (int j = 1; temp != null; j++) {
               // System.out.println("temp:" + temp);
                System.out.println("j:" + j);
              /*  if (temp.contains("四百四十四")) {
                    System.out.println("temp：" + temp);
                    startWrite = true;
                }*/
                temp="";
                if (j==80000000) {
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

    static  String path = "app/src/main/java/com/example/simple/rendaozhizun.txt";
    static  String path1 = "app/src/main/java/com/example/simple";
    public static void main(String[] agrs) {

        File file = new File(path);
        if (file.exists()) {
           // replaceTxtByStr(path);
          //  splitTxt(2);
          //  splitTxt(4);

            split(path,2,path1);
        } else {
            System.out.println("file is not exits");
        }
        System.out.println(" finish");
    }


    public static void splitTxt(int count) {
        try {
            FileReader read = new FileReader(path);
            BufferedReader br = new BufferedReader(read);

            String row;
            List<FileWriter> flist = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                flist.add(new FileWriter("app/src/main/java/com/example/simple/rendaozhizun" + i + ".txt"));
            }
            int rownum = 1;// 计数器
            while ((row = br.readLine()) != null) {
                System.out.println("rownum:"+rownum);
                flist.get(rownum % count).append(row + "\r\n");
                rownum++;
            }
            for (int i = 0; i < flist.size(); i++) {
                flist.get(i).close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void ReadData(int count) {
        try {
            FileReader read = new FileReader(path);
            BufferedReader br = new BufferedReader(read);
            String row;
            int rownum = 1;
            FileWriter fw1 = new FileWriter("app/src/main/java/com/example/simple/text1.txt");
            FileWriter fw2 = new FileWriter("app/src/main/java/com/example/simple/text2.txt");
            FileWriter fw3 = new FileWriter("app/src/main/java/com/example/simple/text3.txt");
            FileWriter fw4 = new FileWriter("app/src/main/java/com/example/simple/text4.txt");
            while ((row = br.readLine()) != null) {
                if (rownum % count == 1) {
                    fw1.append(row + "\r\n");
                } else if (rownum % count == 2) {
                    fw2.append(row + "\r\n");
                } else if (rownum % count == 3) {
                    fw3.append(row + "\r\n");
                } else if (rownum % count == 0) {
                    fw4.append(row + "\r\n");
                }
                rownum++;
            }
            fw1.close();
            fw2.close();
            fw3.close();
            fw4.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void split(String src,int fileSize,String dest){

        if("".equals(src)||src==null||fileSize==0||"".equals(dest)||dest==null){
            System.out.println("分割失败");
        }

        File srcFile = new File(src);//源文件

        long srcSize = srcFile.length();//源文件的大小
        long destSize = 1024*1024*fileSize;//目标文件的大小（分割后每个文件的大小）

        int number = (int)(srcSize/destSize);
        number = srcSize%destSize==0?number:number+1;//分割后文件的数目

        //String fileName = src.substring(src.lastIndexOf("\\"));//源文件名
        String fileName = "rendaozhizun";//源文件名

        InputStream in = null;//输入字节流
        BufferedInputStream bis = null;//输入缓冲流
        byte[] bytes = new byte[1024*1024];//每次读取文件的大小为1MB
        int len = -1;//每次读取的长度值
        try {
            in = new FileInputStream(srcFile);
            bis = new BufferedInputStream(in);
            for(int i=0;i<number;i++){

                String destName = dest+File.separator+fileName+"-"+i+".txt";
                OutputStream out = new FileOutputStream(destName);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int count = 0;
                while((len = bis.read(bytes))!=-1){
                    bos.write(bytes, 0, len);//把字节数据写入目标文件中
                    count+=len;
                    if(count>=destSize){
                        break;
                    }
                }
                bos.flush();//刷新
                bos.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭流
            try {
                if(bis!=null)bis.close();
                if(in!=null)in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


