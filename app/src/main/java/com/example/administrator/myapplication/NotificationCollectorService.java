package com.example.administrator.myapplication;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.os.Build;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationCollectorService extends NotificationListenerService {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        //Log.i("xiaolong", "open" + "-----" + sbn.getPackageName());
        Log.i("xiaolong", "tickerText" + "------" + sbn.getNotification().tickerText);
        Log.i("xiaolong", "title" + "-----" + sbn.getNotification().extras.get("android.title"));
        Log.i("xiaolong", "text" + "-----" + sbn.getNotification().extras.get("android.text"));
        Object title_obj=sbn.getNotification().extras.get("android.title");
        Object text_obj=sbn.getNotification().extras.get("android.text");
        if(title_obj!=null && text_obj!=null) {
            String title = sbn.getNotification().extras.get("android.title").toString();
            String text  = sbn.getNotification().extras.get("android.text").toString();

            if (title.equals("支付宝通知")) {
                System.out.println("进入支付宝");
                int i = text.indexOf("成功收款");
                if (i > -1) {
                    Log.i("index of 成功收款:", String.valueOf(i));
                    String money_text = text.substring("成功收款".length());
                    int indexof_yuan = money_text.indexOf("元");
                    String money = "";
                    if (indexof_yuan > -1) {
                        money = money_text.substring(0, indexof_yuan);
                        double money_double = Double.parseDouble(money);
                        System.out.println(money_double);
                        postToGetMoney pm = new postToGetMoney();
                        pm.get(money);
                    }
                }
            }


        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("xiaolong", "remove" + "-----" + sbn.getPackageName());

    }


}
