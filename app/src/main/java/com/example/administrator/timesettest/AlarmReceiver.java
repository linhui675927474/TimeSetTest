package com.example.administrator.timesettest;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent){

        int count=intent.getIntExtra("count", 0);
        Log.e("AlarmReceiver", "onReceive "+count);

        Intent intent1=new Intent(context, ResponseActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra("count",count);
        context.startActivity(intent1);

        Intent intent2=new Intent(context, TimeSetService.class);
        intent2.putExtra("count",count);
        context.startService(intent2);

        /**
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification= new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("定时到！")
                .setContentText("定时过去"+startID+"次啦")
                .build();
        manager.notify(1,notification);
*/

    }
}
