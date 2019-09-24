package com.example.mk141.boundservice;
import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.IBinder;
import android.os.Binder;
import java.util.Date;
import java.util.Locale;
public class Show_Time_Service extends Service
{
    private final IBinder activity_service_binder=new MyLocalBinder();//bridge b/t client and
    public Show_Time_Service()                                       //server
    {
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return activity_service_binder;
    }
    public String get_current_date()
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss", Locale.US);
        return dateFormat.format(new Date());
    }
    public class MyLocalBinder extends Binder
    {
        Show_Time_Service getService()
        {
            return Show_Time_Service.this;
        }
    }
}