package com.example.simple.model.recognizer.kdxf;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.simple.model.recognizer.IRecognizerResult;
import com.example.simple.model.recognizer.IReconginer;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 科大讯飞语音识别帮助类
 * 语音转文字
 * create by guozhk on 2018/7/23
 **/
public class SpeechRecognizerUtil implements IReconginer {
    private String TAG = SpeechRecognizerUtil.class.getSimpleName();
    //语音听写对象
    private SpeechRecognizer mSpeechRecognizer;
    private boolean mInit = false;
    private IRecognizerResult mIRecognizerResult;

    private SpeechRecognizerUtil() {
    }

    private static class SpeechRecognizerUtilInstant {
        public static SpeechRecognizerUtil INSTANST = new SpeechRecognizerUtil();
    }

    public static SpeechRecognizerUtil getInstance() {
        return SpeechRecognizerUtilInstant.INSTANST;
    }

    public SpeechRecognizerUtil init(Context context,IRecognizerResult mIRecognizerResult) {
        this.mIRecognizerResult = mIRecognizerResult;
        mSpeechRecognizer = SpeechRecognizer.createRecognizer(context, mInitListener);
        return this;
    }


    /**
     * 开始识别
     */
    @Override
    public void startRecognizer() {
        if (!mInit) {
            new Throwable("speechRecognizer is not init...");
        }
        setParms();
        mSpeechRecognizer.startListening(mRecognizerListener);
    }

    /**
     * 停止识别
     */
    @Override
    public void stopRecognizer() {
        mSpeechRecognizer.stopListening();
    }

    @Override
    public void cancelRecognizer() {
        mSpeechRecognizer.cancel();
    }

    /**
     * 语音听写对象 初始化监听
     */
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int i) {
            if(mIRecognizerResult!=null){
                mIRecognizerResult.initSucc(i);
            }
            if (i == ErrorCode.SUCCESS) {
                mInit = true;
            } else {
                Log.e(TAG, "error code : " + i + "  init fail....");
            }
        }
    };


    /**
     * 语音识别 监听函数
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {
        private String result;

        @Override
        public void onVolumeChanged(int i, byte[] bytes) {
            // Log.e(TAG,"onVolumeChanged... i:"+i);
            if(mIRecognizerResult!=null){
                mIRecognizerResult.onVolumeChanged(i);
            }
        }

        @Override
        public void onBeginOfSpeech() {
            result = null;
            Log.e(TAG, "onBeginOfSpeech...");
            if(mIRecognizerResult!=null){
                mIRecognizerResult.beginRecognizer();
            }
        }

        @Override
        public void onEndOfSpeech() {
            Log.e(TAG, "onEndOfSpeech...");
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.e(TAG, "onResult......");
            String r=printResult(recognizerResult);
            if(mIRecognizerResult!=null){
                mIRecognizerResult.recognizerResult(r,b);
            }
            if (r != null) {
                if (result == null) {
                    result = printResult(recognizerResult);
                } else {
                    result += printResult(recognizerResult);
                }
            }

            if (b) {
                Log.e(TAG, "onResult...result:" + result);
                result = null;
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.e(TAG, "onError...");
            if(mIRecognizerResult!=null){
                mIRecognizerResult.error(speechError.getErrorCode(),speechError.getErrorDescription());
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
            Log.e(TAG, "onEvent...i:" + i + " i1:" + i1 + "  i2:" + i2);
        }
    };


    private String printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return text;
    }

    private void setParms() {
        // 清空参数
        mSpeechRecognizer.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mSpeechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mSpeechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");
        // 设置语言
        // 设置语言
        mSpeechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域 普通话
        mSpeechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin");


        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mSpeechRecognizer.setParameter(SpeechConstant.VAD_BOS, "3000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mSpeechRecognizer.setParameter(SpeechConstant.VAD_EOS, "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_PTT, "1");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mSpeechRecognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");

    }

  /*  public SpeechRecognizerUtil setIRecognizerResult(IRecognizerResult mIRecognizerResult) {
        this.mIRecognizerResult = mIRecognizerResult;
        return this;
    }*/
}
