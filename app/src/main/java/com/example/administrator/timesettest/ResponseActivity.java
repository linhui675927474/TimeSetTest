package com.example.administrator.timesettest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

public class ResponseActivity extends AppCompatActivity {

    private TextView response;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            if(msg.what<6) {
                response.setText("现在是第 " + msg.what + "次5s定时");

            }else {
                response.setText("6次定时结束");
                Intent intent=new Intent(ResponseActivity.this, TimeSetService.class);
                stopService(intent);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response);
        Intent intent=getIntent();
        int count= intent.getIntExtra("count",0);
        response=findViewById(R.id.response);
        Message message=new Message();
        message.what=count;
        handler.sendMessage(message);
    }

    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        int count= intent.getIntExtra("count",0);
        response=findViewById(R.id.response);
        Message message=new Message();
        message.what=count;
        handler.sendMessage(message);
    }





}