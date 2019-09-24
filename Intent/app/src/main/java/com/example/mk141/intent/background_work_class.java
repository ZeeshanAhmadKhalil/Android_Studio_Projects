package com.example.mk141.intent;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
public class background_work_class extends IntentService
{
    private static final String TAG="com.example.mk141.inten";
    public background_work_class()
    {
        super("background_work_class");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.i(TAG,"Service Started");
    }
}