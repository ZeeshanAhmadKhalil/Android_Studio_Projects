package com.example.mk141.threads;
//import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;// Can not update interface inside thread
import android.os.Message;// So we use handler and Message for this
public class MainActivity extends AppCompatActivity
{
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText("Calculation is Done");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view)
    {
        Runnable r=new Runnable()
        {
            @Override
            public void run()
            {
                long future_time=System.currentTimeMillis()+10000;
                while (System.currentTimeMillis()<=future_time)
                {
                    synchronized (this)
                    {
                        try
                        {
                            wait(future_time-System.currentTimeMillis());
                        }       catch (Exception e)
                        {}
                    }
                }
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread=new Thread(r);
        thread.start();
    }
}