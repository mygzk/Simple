package com.example.simple.model.pgy;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.dialog.DialogHelper;
import com.example.simple.widget.UpdateProgressDiolog;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

/**
 * create by guozhk on 2019/3/28 11:25
 **/
public class PgyHelper {
    private FragmentManager manager;
    private Context context;


    public PgyHelper(FragmentManager manager, Context context) {
        this.manager = manager;
        this.context = context;
    }

  /*  public PgyHelper(FragmentManager manager) {
        this.manager = manager;
    }*/


    public void checkUpdate() {
        /** 新版本 **/
        new PgyUpdateManager.Builder()
                .setForced(true)                //设置是否强制提示更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk， 默认为true
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is no new version");
                    }

                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        //有更新回调此方法
                        Log.d("pgyer", "there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener

                        updateDialog(appBean);
                        //  PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
                        Log.e("pgyer", "check update failed ", e);
                    }
                })
                //注意 ：
                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
                //想要使用蒲公英的默认下载进度的UI则不设置此方法
                .setDownloadFileListener(new DownloadFileListener() {
                    @Override
                    public void downloadFailed() {
                        //下载失败
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        if (diolog != null) {
                            diolog.dismiss();
                        }
                        // 使用蒲公英提供的安装方法提示用户 安装apk
                        PgyUpdateManager.installApk(uri);
                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        if (integers != null) {
                            updateProgress(integers[0]);
                        }

                        Log.e("pgyer", "update download apk progress" + integers[0]);
                    }
                })
                .register();
    }


    private void updateDialog(final AppBean appBean) {

        DialogHelper.Builder builder = new DialogHelper.Builder();

        builder.layoutResId(R.layout.dialog_pgy_update_tip)
                .clickId(R.id.dilao_update_btn_cancle, R.id.dilao_update_btn_sure)
                .setOnViewClick(new DialogHelper.OnViewClick() {

                    @Override
                    public void click(View v, DialogFragment dialog) {
                        switch (v.getId()) {
                            case R.id.dilao_update_btn_cancle:
                                dialog.dismiss();

                                break;
                            case R.id.dilao_update_btn_sure:
                                PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                                break;
                        }
                    }


                })
                .bindView(new DialogHelper.IBindView() {
                    @Override
                    public void bindView(View view) {
                        String s = "版本名称:" + appBean.getVersionName() + "\n版本号:" + appBean.getVersionCode() + "\n版本说明:" + appBean.getReleaseNote();
                        TextView tvTip = view.findViewById(R.id.dilao_update_content_tip);
                        tvTip.setText(s);

                    }
                })
                .setGravity(Gravity.CENTER)
        ;

        DialogHelper dialogHelper = builder.create();
        dialogHelper.show(manager, "dialog");
    }

    UpdateProgressDiolog diolog;

    private void updateProgress(int progress) {
        if (diolog == null) {
            diolog = new UpdateProgressDiolog(context);

            diolog.updateToatalSize(100);
        }

        diolog.updateProcess(progress);
        if (!diolog.isShowing()) {
            diolog.show();
        }

    }


}
