package com.example.mk141.animationandtransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.graphics.Color;
public class MainActivity extends AppCompatActivity
{
    Button button;
    int counter=4;
    ViewGroup main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_layout=(ViewGroup)findViewById(R.id.main_layout);
        main_layout.setOnTouchListener
        (
            new RelativeLayout.OnTouchListener()
            {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent)
                {
                    button=(Button) findViewById(R.id.button);
                    TransitionManager.beginDelayedTransition(main_layout);
                    counter--;
                    if(counter==3)
                        move_bottom_right();
                    else if(counter==2)
                        move_bottom_left();
                    else if(counter==1)
                        move_top_right();
                    else if(counter==0)
                        move_top_left();
                    else
                    {
                        move_center();
                        counter=4;
                    }
                    return true;
                }
            }
        );
    }
    public void move_bottom_right()
    {
        button.setText("Botton Right");
        button.setBackgroundColor(Color.RED);
        button.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams position_params=new RelativeLayout.LayoutParams
        (
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        position_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        position_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        button.setLayoutParams(position_params);
        ViewGroup.LayoutParams size_params=button.getLayoutParams();
        size_params.width=500;
        size_params.height=500;
        button.setLayoutParams(size_params);
    }
    public void move_bottom_left()
    {
        button.setText("Botton left");
        button.setBackgroundColor(Color.BLUE);
        button.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams position_params=new RelativeLayout.LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
        position_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        position_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        button.setLayoutParams(position_params);
        ViewGroup.LayoutParams size_params=button.getLayoutParams();
        size_params.width=400;
        size_params.height=400;
        button.setLayoutParams(size_params);
    }
    public void move_top_right()
    {
        button.setText("Top Right");
        button.setBackgroundColor(Color.GREEN);
        button.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams position_params=new RelativeLayout.LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
        position_params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        position_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        button.setLayoutParams(position_params);
        ViewGroup.LayoutParams size_params=button.getLayoutParams();
        size_params.width=300;
        size_params.height=300;
        button.setLayoutParams(size_params);
    }
    public void move_top_left()
    {
        button.setText("Top left");
        button.setBackgroundColor(Color.YELLOW);
        button.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams position_params=new RelativeLayout.LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
        position_params.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        position_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        button.setLayoutParams(position_params);
        ViewGroup.LayoutParams size_params=button.getLayoutParams();
        size_params.width=200;
        size_params.height=200;
        button.setLayoutParams(size_params);
    }
    public void move_center()
    {
        button.setText("*");
        button.setBackgroundColor(Color.BLACK);
        button.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams position_params=new RelativeLayout.LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
        position_params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        position_params.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        button.setLayoutParams(position_params);
        ViewGroup.LayoutParams size_params=button.getLayoutParams();
        size_params.width=100;
        size_params.height=100;
        button.setLayoutParams(size_params);
    }
}
