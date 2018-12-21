package com.example.simple.model.fileselect;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by guozhk on 2018/12/20.
 */

public class FileUtil {

    private String TAG = FileUtil.class.getSimpleName();

    public void findFiles(String path) {

        if (TextUtils.isEmpty(path)) {
            // path = Environment.getExternalStorageDirectory().getPath();

            path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();

        }
       // Log.e(TAG, "path:" + path);

        final String finalPath = path;
        new Thread(new Runnable() {
            @Override
            public void run() {

                File dirFile = new File(finalPath);

                if (dirFile.exists()) {
                    if (dirFile.isDirectory()) {
                        File[] files = dirFile.listFiles();
                        if (files != null) {
                            Arrays.sort(files, new Comparator<File>() {
                                @Override
                                public int compare(File o1, File o2) {

                                    if (o1.isDirectory() && o2.isFile())
                                        return -1;
                                    if (o1.isFile() && o2.isDirectory())
                                        return 1;
                                    return o1.getName().compareTo(o2.getName());
                                }
                            });

                            final List<FileBean> fileBeans = new ArrayList<>();
                            for (File file : files) {
                                if (!file.isHidden()) {
                                    FileBean bean = new FileBean();
                                    bean.file = file;
                                    bean.name = file.getName();
                                    bean.isDir = file.isDirectory();
                                    bean.path = file.getAbsolutePath();
                                    fileBeans.add(bean);
                                }
                            }

                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mIResulitCallback != null) {
                                        mIResulitCallback.result(fileBeans);
                                    }
                                }
                            });


                        } else {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mIResulitCallback != null) {
                                        mIResulitCallback.result(null);
                                    }
                                }
                            });
                        }

                    } else {
                        //不是目录
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (mIResulitCallback != null) {
                                    mIResulitCallback.fail("该文件不是文件夹");
                                }
                            }
                        });
                    }
                } else {
                    //目录不勋在
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (mIResulitCallback != null) {
                                mIResulitCallback.fail("目录不存在");
                            }
                        }
                    });
                }


            }
        }).start();

    }


    private IResultCallback mIResulitCallback;

    public void setIResulitCallback(IResultCallback mIResulitCallback) {
        this.mIResulitCallback = mIResulitCallback;
    }

    public interface IResultCallback {

        void result(List<FileBean> result);

        void fail(String msg);


    }





}
