package com.example.simple.model.fileselect;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2018/12/20.
 */

public class FileBean {

    public static int FILE_TYPE_IMG = 1;
    public static int FILE_TYPE_APK = 2;
    public static int FILE_TYPE_WORD = 3;
    public static int FILE_TYPE_EXCEL = 4;
    public static int FILE_TYPE_PDF = 5;
    public static int FILE_TYPE_TXT = 6;
    public static int FILE_TYPE_UNKONWN = 7;


    public String name; //文件名称
    public String path; //文件路径
    public boolean isDir;//是否文件夹
    public boolean isSelect;//是否被选中
    public File file;//文件


    public List<FilePath> getPathData() {

        return getPathData(path);
    }


    public static List<FilePath> getPathData(String path) {
        String p = path;
        if (TextUtils.isEmpty(p)) {
            return null;
        }
        String rootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();

        if (!p.contains(rootPath)) {
            return null;
        }

        p = path.replace(rootPath, "");

        String[] pathData = p.split(File.separator, -1);

        if (pathData != null) {
            String p1 = rootPath;
            List<FilePath> pData = new ArrayList<>();

            for (String pp : pathData) {
                if (TextUtils.isEmpty(pp)) {
                    continue;
                }
                FilePath filePath = new FilePath();
                filePath.currentpath = pp.replace("/", "");
                p1 = p1 + File.separator + pp;
                filePath.path = p1;
                pData.add(filePath);
            }

            return pData;
        }
        return null;
    }


    static public class FilePath {
        public String currentpath;
        public String path;
    }


    public int getFileType() {

        if (TextUtils.isEmpty(name)) {
            return FILE_TYPE_UNKONWN;
        }


        if (name.endsWith(".jpg") ||
                name.endsWith(".png") ||
                name.endsWith(".JPG") ||
                name.endsWith("JPEG") ||
                name.endsWith(".PNG") ||
                name.endsWith(".jpeg") ||
                name.endsWith(".gif") ||
                name.endsWith(".GIF")) {

            return FILE_TYPE_IMG;
        }
        if (name.endsWith(".apk")) {
            return FILE_TYPE_APK;
        }


        if (name.endsWith(".doc") ||
                name.endsWith(".docx") ||
                name.endsWith(".DOC") ||
                name.endsWith(".DOCX")) {
            return FILE_TYPE_WORD;
        }

        if (name.endsWith(".xls")) {
            return FILE_TYPE_EXCEL;
        }


        if (name.endsWith(".pdf")) {
            return FILE_TYPE_PDF;
        }


        if (name.endsWith(".txt")) {
            return FILE_TYPE_TXT;
        }


        return FILE_TYPE_UNKONWN;
    }

}
