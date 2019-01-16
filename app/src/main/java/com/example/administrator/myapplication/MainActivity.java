package com.example.administrator.myapplication;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String string = Settings.Secure.getString(getContentResolver(),
                "enabled_notification_listeners");
        System.out.println("zyf 已经允许使用通知权的应用:" + string);
        if (!string.contains(NotificationCollectorService.class.getName())) {
            startActivity(new Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }
}
