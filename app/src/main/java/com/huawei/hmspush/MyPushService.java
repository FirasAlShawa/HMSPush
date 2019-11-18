package com.huawei.hmspush;

import android.util.Log;
import android.widget.TextView;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

public class MyPushService extends HmsMessageService {

    private static final String TAG = "PushDemoLog";
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG, "receive token:" + s);
        System.out.println("receive token:" + s);

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().length() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
            System.out.println("Message data payload: " + remoteMessage.getData());

        }
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            System.out.println("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onMessageSent(String s) {
    }

    @Override
    public void onSendError(String s, Exception e) {
    }
}