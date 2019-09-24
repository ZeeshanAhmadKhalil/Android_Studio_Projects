package com.example.mk141.sharedbear;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText user_name;
    EditText user_password;
    TextView info_display;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name=findViewById(R.id.user_name);
        user_password=findViewById(R.id.user_password);
        info_display=findViewById(R.id.info_display);
    }
    public void enter_information(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_name",user_name.getText().toString());
        editor.putString("user_password",user_password.getText().toString());
        editor.apply();
        Toast.makeText(MainActivity.this,"Information Saved!",Toast.LENGTH_SHORT).show();
    }
    public void display_information(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("user_name","");
        String password=sharedPreferences.getString("user_password","");
        info_display.setText(name+" "+password);
    }
}
