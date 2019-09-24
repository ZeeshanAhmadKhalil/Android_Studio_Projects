package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;

public class Animation
{
    private Bitmap[] frames;
    private int current_frame;
    private long start_time;
    private long delay;
    private boolean played_once;

    public void setFrames(Bitmap[] frames)
    {
        this.frames = frames;
        current_frame=0;
        start_time=System.nanoTime();
    }

    public void setDelay(long delay)
    {
        this.delay = delay;
    }

    public void setCurrent_frame(int current_frame)
    {
        this.current_frame = current_frame;
    }
    public void update()
    {
        long elipsed=(System.nanoTime()-start_time)/1000000;
        if (elipsed>delay)
        {
            current_frame++;
            start_time=System.nanoTime();
        }
        if (current_frame==frames.length)
        {
            current_frame=0;
            played_once=true;
        }
    }

    public Bitmap get_image()
    {
        return frames[current_frame];
    }

    public int getCurrent_frame()
    {
        return current_frame;
    }
    public boolean get_palyed_once()
    {
        return played_once;
    }
}
