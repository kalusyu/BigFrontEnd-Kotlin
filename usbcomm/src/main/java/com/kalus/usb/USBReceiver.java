package com.kalus.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class USBReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("ybw", "action === " + intent.getAction());
        if (intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {//U盘插入
            String path = intent.getDataString();
            if (!TextUtils.isEmpty(path)) {
                String[] pathArr = path.split("file://");
                if (pathArr.length == 2) {
                    String pathString = pathArr[1];//U盘路径
                    Toast.makeText(context, pathString, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (intent.getAction().equals("android.intent.action.MEDIA_UNMOUNTED")) {//U盘拔出
            // doSomething

        } else if (intent.getAction().equals("android.intent.action.MEDIA_REMOVED")) { // 完全拔出

        }
    }
}
 