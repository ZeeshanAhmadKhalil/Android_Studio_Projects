package com.example.mk141.myfirstandroidgame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private int fps=30;
    private double avg_fps;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private Canvas canvas;//canvas is used to draw something
    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel)
    {
        super();
        this.surfaceHolder=surfaceHolder;
        this.gamePanel=gamePanel;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void run()
    {
        long start_time;
        long time_millis;
        long wait_time;
        long total_time=0;
        int frame_count=0;
        long target_time=1000/fps;
        while (running)
        {
            start_time=System.nanoTime();
            canvas=null;
            try
            {
                canvas=this.surfaceHolder.lockCanvas();//surfaceHolder is a content view
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }
            catch (Exception e)
            {

            }
            finally
            {
                if (canvas!=null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            time_millis=(System.nanoTime()-start_time)/1000000;
            wait_time=target_time-time_millis;
            try
            {
                this.wait(wait_time);//this.sleep(wait_time) not working
            }
            catch (Exception e)
            {

            }
            total_time+=System.nanoTime()-start_time;
            frame_count++;
            if (frame_count==fps)
            {
                avg_fps=1000/((total_time/frame_count)/1000000);
                total_time=0;
                frame_count=0;
                System.out.println("Average Frame Per Second="+avg_fps);
            }
        }
    }
    public void set_running(boolean b)
    {
        running=b;
    }
}
