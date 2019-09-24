package com.example.mk141.videoplayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VideoView videoView=findViewById(R.id.videoView);
//        videoView.setVideoPath("https://www.youtube.com/watch?v=a3ICNMQW7Ok");
//        videoView.start();
    }
}