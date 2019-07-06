package com.org.orm.demo.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.FileFilter;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            UserEntity.class,
    };

    public static void main(String[] args) throws Exception {
        File file = new File(".");
        System.out.println("file path:" + file.getAbsolutePath());
        File findFile = findRawDir(file);
        System.out.println("findFile path:" + findFile.getAbsolutePath());

          writeConfigFile("ormlite_config.txt", classes);
    }

   /* public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }*/


   /* protected static File findRawDir(File dir) {
        for (int i = 0; dir != null && i < 20; i++) {
            File rawDir = findResRawDir(dir);
            if (rawDir != null) {
                return rawDir;
            }
            dir = dir.getParentFile();
        }
        return null;
    }

    *//**
     * Look for the resource directory with raw beneath it.
     *//*
    private static File findResRawDir(File dir) {
        for (File file : dir.listFiles()) {
            if (file.getName().equals(RESOURCE_DIR_NAME) && file.isDirectory()) {
                File[] rawFiles = file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getName().equals(RAW_DIR_NAME) && file.isDirectory();
                    }
                });
                if (rawFiles.length == 1) {
                    return rawFiles[0];
                }
            }
        }
        return null;
    }
*/
}
