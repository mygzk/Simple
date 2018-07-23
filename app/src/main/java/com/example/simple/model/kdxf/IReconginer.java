package com.example.simple.model.kdxf;

/**
 * create by guozhk on 2018/7/23
 **/
public interface IReconginer {

    /**
     * 开始识别语音
     */
    void startRecognizer();

    /**
     * 停止识别语音
     */
    void stopRecognizer();

    /**
     * 取消识别
     */
    void cancelRecognizer();
}
