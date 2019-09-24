package com.example.mk141.boundservice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mk141.boundservice.Show_Time_Service.MyLocalBinder;


public class MainActivity extends AppCompatActivity
{
    Show_Time_Service my_service;
    boolean is_bound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,Show_Time_Service.class);
        bindService(intent,time_service_connection, Context.BIND_AUTO_CREATE);
    }
    public void show_time(View view)
    {
        String current_time= my_service.get_current_date();
        TextView time_text=(TextView)findViewById(R.id.time_text);
        time_text.setText(current_time);
    }
    private ServiceConnection time_service_connection=new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            MyLocalBinder binder=(MyLocalBinder) iBinder;
            my_service=binder.getService();
            is_bound=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            is_bound=false;
        }
    };
}
