package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Hero extends Game_Object
{
    private Bitmap sprite_sheet;
    private float dya;
    private int score;
    private int up;
    private boolean playing;
    private Animation animation=new Animation();
    private long start_time;
    public  Hero(Bitmap bitmap,int w,int h,int no_of_frames)
    {
        x=100;
        y=GamePanel.HEIGHT/3;
        dya=0;
        score=0;
        width=w;
        height=h;
        Bitmap[] image=new Bitmap[no_of_frames];
        sprite_sheet=bitmap;
        for (int i=0;i<no_of_frames;i++)
            image[i]=Bitmap.createBitmap(sprite_sheet,i*width,0,width,height);
        animation.setFrames(image);
        animation.setDelay(10);
        start_time=System.nanoTime();

    }

    public void setUp(int up)
    {
        this.up = up;
    }

    public void update()
    {
        long elipsed=(System.nanoTime()-start_time)/1000000;
        if (elipsed>100)
        {
            score++;
            start_time=System.nanoTime();
        }
        animation.update();
//        if (up==1)
//            dy=(int)(dya-=1);
//        else if (up==-1)
//            dy=(int)(dya+=1);
//        if (dy>1)
//            dy=1;
//        if (dy<-1)
//            dy=-1;
//        y+=dy*2;
//        dy=0;
        if (up<0)
        {
            dy=up;
        }
        else if (up>0)
        {
            dy=up;
        }
        else
        {
            if (dy!=0)
            {
                if (dy>0)
                    dy--;
                else
                    dy++;
            }
        }
        y+=dy*2;

    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.get_image(),x,y,null);
    }

    public int getScore()
    {
        return score;
    }

    public void setPlaying(boolean playing)
    {
        this.playing = playing;
    }
    public boolean getPlaying()
    {
        return playing;
    }
    public void reset_dya()
    {
        dya=0;
    }
    public void reset_score()
    {
        score=0;
    }
    public int getUp() {
        return up;
    }
}
