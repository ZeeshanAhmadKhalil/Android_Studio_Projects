package com.example.mk141.sendbroadcast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity
{
    boolean pressed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void send_broadcast(View view)
    {
        Button sender_btn=(Button)findViewById(R.id.sender_btn);
        if(pressed)
        {
            pressed=false;
            sender_btn.setText("SEND BROADCAST");
            return;
        }
        Intent i=new Intent();
        i.setAction("com.example.mk141.sendbroadcast");
        i.addFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
        sender_btn.setText("SENDED");
        pressed=true;
    }
}
