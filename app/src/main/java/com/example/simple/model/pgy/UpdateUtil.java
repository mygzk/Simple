package com.example.simple.model.pgy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.simple.MyApp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.pgyer.com/apiv2/app/install?appKey=d03390c7201e0692e17fcc6d955e6a68&_api_key=706461e1d6932d01f8c9a2638bf7b4df
 * http://www.pgyer.com/app/installUpdate/cf068088f97ad4d2d3be6f488d88e7cc?sig=tKvHMGUDdAU%2FkL5mOh0yEm7WmwC1vanPl52EWssUz%2FDQIU%2B1KfYxvZyrKbMS8DjC
 * create by guozhk on 2019/3/28 18:36
 **/
public class UpdateUtil {
    private String mDownUrl = "http://www.pgyer.com/app/installUpdate/cf068088f97ad4d2d3be6f488d88e7cc?sig=tKvHMGUDdAU%2FkL5mOh0yEm7WmwC1vanPl52EWssUz%2FDQIU%2B1KfYxvZyrKbMS8DjC";

    private String mApkName = "test" + "_.apk";
    //保存apk目录
    private String mSavePath;


    private final int MSG_DOWN_SATRTA = 1;
    private final int MSG_DOWNING = 2;
    private final int MSG_DOWN_FINISH = 3;
    private final int MSG_DOWN_ERROR = 4;
    private final int MSG_DOWN_NO_SCARD = 5;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DOWN_SATRTA:
                    int total = (int) msg.obj;
                    //  mProgressDialog.setMax(total);
                    break;
                case MSG_DOWNING:
                    int curProgress = (int) msg.obj;
                    // mProgressDialog.setProgress(curProgress);
                    break;
                case MSG_DOWN_FINISH:
                    installApk(new File(mSavePath));
                    break;
                case MSG_DOWN_ERROR:
                    break;
                case MSG_DOWN_NO_SCARD:
                    break;

            }
        }
    };


    public UpdateUtil() {
        mSavePath = Environment.getExternalStorageDirectory() + File.separator + mApkName;
    }


    public void toDownAPk() {
        if (TextUtils.isEmpty(mDownUrl)) {

            return;
        }

        final String uri = mDownUrl;
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    getFileFromServer(uri);
                } catch (Exception e) {
                    //下载apk失败
                    mHandler.sendEmptyMessage(MSG_DOWN_ERROR);
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    private void getFileFromServer(String uri) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的

        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //获取到文件的大小
        // pd.setMax(conn.getContentLength());
        Log.e("down", "getContentLength:" + conn.getContentLength());
        mHandler.sendMessage(mHandler.obtainMessage(MSG_DOWN_SATRTA, conn.getContentLength()));
        InputStream is = conn.getInputStream();
        File file = new File(mSavePath);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] buffer = new byte[1024];
        int len;
        int total = 0;
        while ((len = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
            total += len;
            //获取当前下载量
            //   pd.setProgress(total);
            Log.e("down", "total:" + total);
            mHandler.sendMessage(mHandler.obtainMessage(MSG_DOWNING, total));
        }
        mHandler.sendEmptyMessage(MSG_DOWN_FINISH);
        fos.close();
        bis.close();
        is.close();


    }


    public void testInstall() {
      //  mSavePath = Environment.getExternalStorageDirectory() + File.separator +"zz"+ File.separator+ mApkName;
        mSavePath = Environment.getExternalStorageDirectory() + File.separator + mApkName;
        File file = new File(mSavePath);
        if (file.exists()) {
            installApk(file);
        }
    }

    /**
     * 安装apk
     */
    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addCategory("android.intent.category.DEFAULT");
            Uri contentUri = FileProvider.getUriForFile(MyApp.getApp(), "com.example.simple.fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setAction(Intent.ACTION_DEFAULT);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            Uri contentUri = Uri.fromFile(file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        MyApp.getApp().startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    public boolean haswSdcard() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mHandler.sendEmptyMessage(MSG_DOWN_NO_SCARD);
            return false;
        }
        return true;
    }


    private ICheckResult mCheckresult;

    public void setCheckresult(ICheckResult mCheckresult) {
        this.mCheckresult = mCheckresult;
    }

    public interface ICheckResult {

        void closeNoUpdate();

    }


    public ICheckResult mDefaultCheck = new ICheckResult() {


        @Override
        public void closeNoUpdate() {

        }
    };

}
