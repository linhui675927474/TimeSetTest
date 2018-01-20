package com.example.administrator.timesettest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class TimeSetService extends Service{


    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){

        int count=intent.getIntExtra("count",startID);
        count++;
        Log.e("TimeSetService", "定时开始,"+count);
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int aMin=5*1000;//10s;
        long triggerTime= SystemClock.elapsedRealtime()+aMin;
        Intent intent1=new Intent(this, AlarmReceiver.class);
        intent1.putExtra("count",count);
        PendingIntent pi=PendingIntent.getBroadcast(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.set(AlarmManager.ELAPSED_REALTIME, triggerTime,pi);
        return super.onStartCommand(intent, flags, startID);
    }

    @Override

    public void onDestroy(){
        super.onDestroy();
        //在Service结束后关闭AlarmManager 
        Log.e("TimeSetService","onDestroy");
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Intent i=new Intent(this, AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.cancel(pi);

        Log.e("TimeSetService","定时结束");
    }
}
