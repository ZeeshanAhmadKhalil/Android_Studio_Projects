package com.example.mk141.service;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    private static final String TAG="com.example.mk141.service";
    public MyService()
    {
    }

    @Override
    public void onDestroy()
    {
        Log.i(TAG,"The service is destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG,"The Starting the command");
        Runnable r=new Runnable()             // In this service we need to create thread & in the
        {                                    //Intent Service Thread is automatically created
            @Override
            public void run()
            {
                for(int i=0;i<5;i++)
                {
                    long future_time= System.currentTimeMillis()+10000;
                    while (System.currentTimeMillis()<future_time)
                    {
                        synchronized (this)
                        {
                            try
                            {
                                wait(future_time-System.currentTimeMillis());
                                Log.i(TAG,"The service is doing something");
                            }
                            catch (Exception e)
                            {}
                        }

                    }
                }
            }
        };
        Thread service_thread=new Thread(r);
        service_thread.start();
        return Service.START_STICKY;//START_STICKY restarts & START_NOT_STICKEY does't restarts
    }                              //when android OS stops service

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
