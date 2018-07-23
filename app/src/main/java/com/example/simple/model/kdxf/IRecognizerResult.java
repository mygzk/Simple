package com.example.simple.model.kdxf;

/**
 * create by guozhk on 2018/7/23
 **/
public interface IRecognizerResult {

    /**
     * 初始化chengg
     */
    void initSucc(int code);

    /**
     * 开始识别
     */
    void beginRecognizer();

    /**
     * 音量大小
     * @param i i
     */
    void onVolumeChanged(int i);

    /**
     * 识别结果回到
     *
     * @param str    str
     * @param isLast islast
     */
    void recognizerResult(String str, boolean isLast);

    /**
     * 错误回调
     *
     * @param errorCode errorCode
     * @param msg       msg
     */
    void error(int errorCode, String msg);
}
