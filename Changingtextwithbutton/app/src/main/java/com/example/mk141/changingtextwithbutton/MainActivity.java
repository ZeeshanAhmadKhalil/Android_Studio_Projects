package com.example.mk141.changingtextwithbutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button show_button=(Button)findViewById(R.id.show_button);
        final TextView time_text=(TextView)findViewById(R.id.time_text);
        show_button.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Date currentDate=new Date();
                        String string_date=currentDate.toString();
                        time_text.setText(string_date);
                        time_text.setTextColor(Color.BLACK);
                    }
                }
        );
        show_button.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        time_text.setTextColor(Color.RED);
                        return false;
                    }
                }
        );
    }
}
