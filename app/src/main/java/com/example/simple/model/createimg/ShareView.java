package com.example.simple.model.createimg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.simple.R;
import com.example.simple.utils.DensityUtil;
import com.example.simple.utils.QRCodeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * create by guozhk on 2019/4/28 10:58
 **/
public class ShareView extends FrameLayout {
    private final int IMAGE_WIDTH = 720;
    private final int IMAGE_HEIGHT = 1280;


    private ImageView shareImg;
    private TextView tvInfo;
    private ImageView ImageCode;

    public ShareView(@NonNull Context context) {
        super(context);

        init();
    }

    private void init() {
        View layout = View.inflate(getContext(), R.layout.share_view_layout, this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        layout.setLayoutParams(lp);


        tvInfo = (TextView) layout.findViewById(R.id.tv_info);
        shareImg = (ImageView) layout.findViewById(R.id.share_img);
        ImageCode = (ImageView) layout.findViewById(R.id.share_img_code);

        LinearLayout.LayoutParams goodsImgLp = (LinearLayout.LayoutParams) shareImg.getLayoutParams();
        goodsImgLp.height = (int) (DensityUtil.getHeightInPx(getContext()) * 0.5);
        shareImg.setLayoutParams(goodsImgLp);

        Bitmap bitmap = QRCodeUtil.createQRCode("http://www.baidu.com");
        if (bitmap != null) {
            ImageCode.setImageBitmap(bitmap);
        }
    }

    /**
     * 设置相关信息
     *
     * @param info
     */
    public void setInfo(String info) {
        tvInfo.setText(info);
    }

    private String mGoodsUrl;

    public void setImg(String url) {
        mGoodsUrl = url;


    }


    public void create() {
        Glide.with(getContext())
                .load(mGoodsUrl)
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<Bitmap> target, boolean b) {
                        if (imgCallback != null) {
                            imgCallback.fail("生成图片失败.,.,.,.");
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap bitmap, String s, Target<Bitmap> target, boolean b, boolean b1) {
                        return false;
                    }
                })
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        shareImg.setImageBitmap(resource);
                        Bitmap bitmap = createImage();
                        saveImage(bitmap);
                    }
                });
    }


    /**
     * 生成图片
     *
     * @return
     */
    private Bitmap createImage() {

        //由于直接new出来的view是不会走测量、布局、绘制的方法的，所以需要我们手动去调这些方法，不然生成的图片就是黑色的。

        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(IMAGE_WIDTH, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(IMAGE_HEIGHT, MeasureSpec.EXACTLY);

        measure(widthMeasureSpec, heightMeasureSpec);
        layout(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        Bitmap bitmap = Bitmap.createBitmap(IMAGE_WIDTH, IMAGE_HEIGHT, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        draw(canvas);

        return bitmap;
    }


    /**
     * 保存bitmap到本地
     *
     * @param bitmap
     * @return
     */
    private void saveImage(final Bitmap bitmap) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = getContext().getCacheDir().getPath() + "/floder";
                File fileFolder = new File(path);
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                String fileName = "shareImage_" + System.currentTimeMillis() + ".png";

                File file = new File(path, fileName);

                if (file.exists()) {
                    file.delete();
                }
                String msg = null;
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    String savePath = file.getAbsolutePath();
                    saveResult(savePath, true);
                } catch (FileNotFoundException e) {
                    msg = "保存失败";
                    saveResult(msg, false);
                    e.printStackTrace();
                } catch (IOException e) {
                    msg = "保存失败";
                    saveResult(msg, false);
                    e.printStackTrace();
                } catch (Exception e) {
                    msg = "保存失败";
                    saveResult(msg, false);
                    e.printStackTrace();
                }


            }
        }).start();

    }

    private void saveResult(final String msg, final boolean succ) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (imgCallback != null) {
                    if (succ) {
                        imgCallback.succuss(msg);
                    } else {
                        imgCallback.fail(msg);
                    }
                }
            }
        });

    }


    private ICreateImgCallback imgCallback;

    public void setIgCallback(ICreateImgCallback imgCallback) {
        this.imgCallback = imgCallback;
    }

    public interface ICreateImgCallback {


        void succuss(String imgPath);

      //  void succuss(Bitmap bitmap);

        void fail(String errorMsg);
    }

}
