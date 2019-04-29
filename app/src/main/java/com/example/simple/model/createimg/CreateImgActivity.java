package com.example.simple.model.createimg;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * create by guozhk on 2019/4/28 10:42
 **/
public class CreateImgActivity extends BaseActivity {

    private ImageView imgResult;
    @Override
    protected int getLayoutId() {
        return R.layout.activit_create_img;
    }

    @Override
    protected void initView() {
        imgResult = queryViewById(R.id.img_result);
        queryViewById(R.id.create_img, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_img:
                createShareImage();
                break;

        }
    }


    public void createShareImage() {
        ShareView shareView = new ShareView(CreateImgActivity.this);
        shareView.setInfo("其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息其他信息");

      //  shareView.setImg("http://img1.imgtn.bdimg.com/it/u=1533275126,1287779573&fm=26&gp=0.jpg");
        shareView.setImg("http://img2.imgtn.bdimg.com/it/u=4120993261,4160206187&fm=26&gp=0.jpg");
        shareView.setIgCallback(new ShareView.ICreateImgCallback() {

            @Override
            public void succuss(String imgPath) {
                Glide.with(CreateImgActivity.this).load(imgPath).into(imgResult);
                Log.e("CreateImgActivity", "imgPath:" + imgPath);

            }

            @Override
            public void fail(String errorMsg) {
                Log.e("CreateImgActivity", "errorMsg:" + errorMsg);
            }
        });
        shareView.create();

    }

    /**
     * 保存bitmap到本地
     *
     * @param bitmap
     * @return
     */
/*    private String saveImage(Bitmap bitmap) {

        String path = getCacheDir().getPath();
        //File path = ();

      *//*  if( Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)){
          Log.e("xxx","12312321321312312");
            path= Environment.getExternalStorageState()+"/sdcard";
        }
*//*
        String fileName = "shareImage.png";

        File file = new File(path, fileName);

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }*/
}
