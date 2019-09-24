package com.example.mk141.intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class Alert extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_alert);
        Bundle alarm_data=getIntent().getExtras();
        if(alarm_data==null) {
            return;
        }
        String alarms_msg=alarm_data.getString("alarms_msg");
        TextView alerts_show=(TextView)findViewById(R.id.alerts_show);
        alerts_show.setText(alarms_msg);
    }
    public void onClick(View view)
    {
        Intent i=new Intent(this,Alarm.class);
        EditText alerts_input=(EditText)findViewById(R.id.alerts_input);
        String alerts_msg=alerts_input.getText().toString();
        i.putExtra("alerts_msg",alerts_msg);
        startActivity(i);
        Intent intent=new Intent(this,background_work_class.class);
        startService(intent);
    }
}