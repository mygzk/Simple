package com.example.simple.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.example.simple.R;
import com.example.simple.model.photo.PhoneImgActivity;

public class NotifyMsgActivity extends BaseActivity {


    private Context mContext;

    @Override
    protected int getLayoutId() {
        mContext = this;
        return R.layout.activity_notify_msg;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.notify_msg, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_msg:
                startActivity(new Intent(this, PhoneImgActivity.class));
                break;
        }
    }


    private void notifyMsg() {
        // 创建一个NotificationManager的引用
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // 定义Notification的各种属性
        Notification notification = new Notification(R.drawable.ic_launcher, "新消息", System.currentTimeMillis());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // 设置通知的事件消息
        CharSequence contentTitle = "Title"; // 通知栏标题
        CharSequence contentText = "Text"; // 通知栏内容
        int id = (int) (System.currentTimeMillis() / 1000);
       /* Intent clickIntent = new Intent(mContext, NotificationClickReceiver.class); //点击通知之后要发送的广播

        PendingIntent contentIntent = PendingIntent.getBroadcast(this.getApplicationContext(), id, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);*/
        notificationManager.notify(id, notification);

    }
}
