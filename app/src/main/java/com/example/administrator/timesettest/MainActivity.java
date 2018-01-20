package com.example.administrator.timesettest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start;
    private Button stop;
    private int count=0;
    //private Button bind;
    //private Button unbind;
    /**
    private TimeSetService.MyBinder myBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder=(TimeSetService.MyBinder)service;
            Intent intent=new Intent(MainActivity.this, TimeSetService.class);
            startService(intent);
            myBinder.response();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
                Intent intentStart=new Intent(MainActivity.this, TimeSetService.class);
                intentStart.putExtra("count",count);
                startService(intentStart);
                Toast.makeText(this, "5s定时开启！",Toast.LENGTH_SHORT ).show();
                break;
            case R.id.stop:
                Intent intentStop=new Intent(MainActivity.this, TimeSetService.class);
                stopService(intentStop);
                break;
                /**
            case R.id.bind:
                Intent intentBind=new Intent(MainActivity.this, TimeSetService.class);
                bindService(intentBind, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(connection);
                break;
                 */
            default:
                break;
        }
    }
}
