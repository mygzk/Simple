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

}
