package com.example.simple.model.recognizer;

/**
 * Created by zonglv on 2018/4/10.
 * 语音翻译回调页面接口
 */
public interface RecogUIIntrface {


    /**
     * 是否展示录音状态下的内容页面
     *
     * @param show
     */
    void showRecogPage(boolean show);

    /**
     * 翻译内容
     */
    void recogContent(String content);

    /**
     * 刷新录音按钮动画效果
     *
     * @param cancelFlag t:取消状态 f:未取消状态
     */
    void refreshRecogBtnUI(boolean cancelFlag);

    /**
     * 录音翻译完毕后产生聊天消息
     *
     * @param recogSr 录音翻译后内容
     */
    void sendRecogMessage(String recogSr);

    /**
     * 在线翻译错误
     *
     * @param errorCode
     * @param errorMessage
     */
    void recogError(int errorCode, String errorMessage);
}
