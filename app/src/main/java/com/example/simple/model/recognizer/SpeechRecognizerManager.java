package com.example.simple.model.recognizer;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.simple.model.recognizer.kdxf.SpeechRecognizerUtil;

/**
 * create by guozhk on 2018/7/23
 **/


public class SpeechRecognizerManager {
    private String TAG = SpeechRecognizerManager.class.getSimpleName();

    //科大讯飞
    public static final int TYPE_KDXF = 1;
    //百度语音
    public static final int TYPE_BD = 2;


    //小于等于最短时间将视为无效录音
    private final int RECOG_CANCEL_TIME = 1 * 1000;
    private final int CANCEL_Y = 200;
    private RecogUIIntrface recogUIIntrface;

    private long downTime;
    private float downY;
    private boolean cancel;//f:未取消 t:取消
    private boolean baiduRecogOver;//t:识别中 f:识别结束


    private IReconginer mIReconginer;

    private SpeechRecognizerManager() {
    }

    private static class MangerInstance {
        static SpeechRecognizerManager INSTANCE = new SpeechRecognizerManager();
    }

    public static SpeechRecognizerManager getInstance() {
        return MangerInstance.INSTANCE;
    }

    public void initRecognizer(Context context, int model) {
        if (model == TYPE_KDXF) {
            mIReconginer = SpeechRecognizerUtil.getInstance()
                    .init(context, mIRecognizerResult);

        } else if (model == TYPE_BD) {

        } else {

        }
    }


    private IRecognizerResult mIRecognizerResult = new IRecognizerResult() {

        @Override
        public void initSucc(int code) {
            Log.e(TAG, "======initSucc=====");
        }

        @Override
        public void onVolumeChanged(int i) {
            //   Log.e(TAG, "======onVolumeChanged=====i:" + i);
        }

        @Override
        public void beginRecognizer() {
            baiduRecogOver = true;
            Log.e(TAG, "======beginRecognizer=====");
        }

        @Override
        public void recognizerResult(String str, boolean isLast) {
            if (isLast) {
                baiduRecogOver = false;
                Log.e(TAG, "======recognizerResult=====str:" + str);

            }

            recogUIIntrface.recogContent(str);
        }

        @Override
        public void error(int errorCode, String msg) {
            Log.e(TAG, "======error=====errorCode:" + errorCode + " msg:" + msg);
            baiduRecogOver = false;
            recogUIIntrface.recogError(errorCode, msg);
        }
    };

    /**
     * 设置触摸状态解析
     *
     * @param v     view
     * @param event event
     * @return boolean
     */
    public boolean recogTouch(View v, MotionEvent event) {
        if (baiduRecogOver) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                cancel = false;
                downY = event.getY();
                if (recogUIIntrface != null) {
                    recogUIIntrface.recogContent("");
                    recogUIIntrface.showRecogPage(true);
                }

                // TODO: 2018/7/23  开始录音
                mIReconginer.startRecognizer();
                return true;
            case MotionEvent.ACTION_MOVE:
                float moveY = Math.abs(event.getY() - downY);
                if (moveY > CANCEL_Y) {
                    cancel = true;
                } else {
                    cancel = false;
                }
                if (recogUIIntrface != null) {
                    recogUIIntrface.refreshRecogBtnUI(cancel);
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (System.currentTimeMillis() - downTime < RECOG_CANCEL_TIME) {
                    //todo 取消录音
                    mIReconginer.stopRecognizer();
                    //无效录音时间
                    //提示页面时间段
                    //恢复到未录音状态
                    if (recogUIIntrface != null) {
                        recogUIIntrface.showRecogPage(false);
                        baiduRecogOver = false;
                    }
                    return true;
                }

                if (cancel) {
                    //todo 取消录音
                    mIReconginer.stopRecognizer();
//                    取消
                    //提示页面已经取消
                    //恢复到未录音状态
                    if (recogUIIntrface != null) {
                        recogUIIntrface.showRecogPage(false);
                    }
                    return true;
                }
                //todo 取消录音
                mIReconginer.cancelRecognizer();
                return true;

        }
        return false;
    }


    public void setRecogUIIntrface(RecogUIIntrface recogUIIntrface) {
        this.recogUIIntrface = recogUIIntrface;
    }
}
