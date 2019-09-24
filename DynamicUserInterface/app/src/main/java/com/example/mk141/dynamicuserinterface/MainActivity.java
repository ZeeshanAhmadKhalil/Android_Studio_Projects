package com.example.mk141.dynamicuserinterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;
//import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout login_layout=new RelativeLayout(this);
        Button login_button=new Button(this);
        EditText user_email=new EditText(this);
        EditText user_Password=new EditText(this);
        login_layout.setBackgroundColor(Color.GREEN);
        login_button.setText("Log In");
        login_button.setBackgroundColor(Color.RED);
        login_button.setTextColor(Color.WHITE);
        Resources app_resource=getResources();
        int pixels=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,300,
                app_resource.getDisplayMetrics());
        login_button.setId(1);
        user_email.setWidth(pixels);
        user_email.setTextColor(Color.RED);
        user_email.setId(2);
        user_Password.setWidth(pixels);
        user_Password.setTextColor(Color.RED);
        user_Password.setId(3);
        RelativeLayout.LayoutParams button_details=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        button_details.addRule(RelativeLayout.CENTER_VERTICAL);
        button_details.addRule(RelativeLayout.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams user_name_details=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        user_name_details.addRule(RelativeLayout.ABOVE,user_Password.getId());
        user_name_details.addRule(RelativeLayout.CENTER_HORIZONTAL);
        user_name_details.setMargins(0,0,0,40);
        RelativeLayout.LayoutParams user_password_details=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        user_password_details.addRule(RelativeLayout.ABOVE,login_button.getId());
        user_password_details.addRule(RelativeLayout.CENTER_HORIZONTAL);
        user_password_details.setMargins(0,0,0,60);
        login_layout.addView(login_button,button_details);
        login_layout.addView(user_email,user_name_details);
        login_layout.addView(user_Password,user_password_details);
        setContentView(login_layout);
    }
}