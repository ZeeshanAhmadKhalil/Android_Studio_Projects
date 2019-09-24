package com.example.mk141.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    NotificationCompat.Builder notification;
    private static final int uniqueID=876345;
    private static final String str_uniqueID="876345";
    Bitmap icon;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon=((BitmapDrawable)getResources().getDrawable(R.drawable.icon)).getBitmap();
        notification=new NotificationCompat.Builder(this,str_uniqueID);
        notification.setAutoCancel(true);
    }
    public void show_notification(View view)
    {
        //Build Notification
        notification.setSmallIcon(R.drawable.icon);
        notification.setLargeIcon(icon);
        notification.setTicker("The Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Defender Notification");
        notification.setContentText("The notification button was pressed");
        //Allow notification to access activities
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //Issue notification
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueID,notification.build());
    }
}
