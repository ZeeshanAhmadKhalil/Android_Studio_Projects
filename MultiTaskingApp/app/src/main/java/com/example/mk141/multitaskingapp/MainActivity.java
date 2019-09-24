package com.example.mk141.multitaskingapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.GridLayout;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener{
    public TextView hello_world;
    public boolean gestures_allowed=false;
    private GestureDetectorCompat gesture_detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello_world=new TextView(this);
        this.gesture_detector=new GestureDetectorCompat(this,this);
        RelativeLayout relative_layout=(RelativeLayout)findViewById(R.id.relative_layout);

        RelativeLayout.LayoutParams hello_world_details=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        hello_world_details.addRule(RelativeLayout.CENTER_HORIZONTAL);
        hello_world_details.addRule(RelativeLayout.CENTER_VERTICAL);
        hello_world.setTextColor(Color.WHITE);
        relative_layout.addView(hello_world,hello_world_details);
        hello_world.setVisibility(View.INVISIBLE);
        Button button=(Button)findViewById(R.id.button);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        Button button4=(Button)findViewById(R.id.button4);
        Button button5=(Button)findViewById(R.id.button5);
        Button up=(Button)findViewById(R.id.up);
        Button down=(Button)findViewById(R.id.down);
        Button left=(Button)findViewById(R.id.left);
        Button right=(Button)findViewById(R.id.right);
        final Button back=(Button)findViewById(R.id.back);
        final GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        final GridLayout Grid_Arrow_Buttons=(GridLayout)findViewById(R.id.Grid_Arrow_Buttons);
        final TextView textView=(TextView)findViewById(R.id.textView);
        button.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        gridLayout.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        hello_world.setVisibility(View.VISIBLE);
                        hello_world.setText("Hello World");
                    }
                }
        );
        button2.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        gridLayout.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        Grid_Arrow_Buttons.setVisibility(View.VISIBLE);
                    }
                }
        );
        button3.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        gridLayout.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        hello_world.setVisibility(View.VISIBLE);
                        Grid_Arrow_Buttons.setVisibility(View.VISIBLE);
                        hello_world.setText("Press White Buttons to View Events");
                    }
                }
        );
        button4.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        gridLayout.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        hello_world.setVisibility(View.VISIBLE);
                        hello_world.setText("Swap, Tap or Double Tap to see gestures");
                        gestures_allowed=true;
                    }
                }
        );
        button5.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        gridLayout.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        hello_world.setVisibility(View.VISIBLE);
                        Grid_Arrow_Buttons.setVisibility(View.VISIBLE);
                        hello_world.setText("Swap, Tap, Double Tap or press white buttons to see Events and gestures");
                        gestures_allowed=true;
                    }
                }
        );
        back.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        back.setVisibility(View.INVISIBLE);
                        Grid_Arrow_Buttons.setVisibility(View.INVISIBLE);
                        hello_world.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        gestures_allowed=false;
                    }
                }
        );
        up.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        hello_world.setText("You Taped Up");
                    }
                }
        );
        down.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        hello_world.setText("You Taped Down");
                    }
                }
        );
        left.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        hello_world.setText("You Taped left");
                    }
                }
        );
        right.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        hello_world.setText("You Taped right");
                    }
                }
        );
        up.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        hello_world.setText("Your are Taped Up For long time");
                        return true;
                    }
                }
        );
        down.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        hello_world.setText("Your are Taped down For long time");
                        return true;
                    }
                }
        );
        left.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        hello_world.setText("Your are Taped left For long time");
                        return true;
                    }
                }
        );
        right.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        hello_world.setText("Your are Taped right For long time");
                        return true;
                    }
                }
        );
        setContentView(relative_layout);
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(gestures_allowed)
            hello_world.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        if(gestures_allowed)
            hello_world.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(gestures_allowed)
            hello_world.setText("onFling");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture_detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}