package com.example.mk141.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class Alarm extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Bundle alert_data=getIntent().getExtras();
        if(alert_data==null)
            return;
        String alerts_msg=alert_data.getString("alerts_msg");
        TextView alarm_show=(TextView)findViewById(R.id.alarm_show);
        alarm_show.setText(alerts_msg);
    }
    public void onClick(View view)
    {
        Intent i=new Intent(this,Alert.class);
        EditText alarm_input=(EditText)findViewById(R.id.alarm_input);
        String alarms_msg=alarm_input.getText().toString();
        i.putExtra("alarms_msg",alarms_msg);
        startActivity(i);
    }
}
