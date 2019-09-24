package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;


public class Enemy extends Game_Object
{
    private int score;
    private int speed;
    private Bitmap sprite_sheet;
    private Random random=new Random();
    private Animation animation=new Animation();
    public Enemy(Bitmap bitmap,int x,int y,int w,int h,int s,int no_of_frames)
    {
        super.x=x;
        super.y=y;
        width=w;
        height=h;
        score=s;
        speed=4+(int)(random.nextDouble()*score/120);
        if (speed>40)
            speed=40;
        Bitmap[] image=new Bitmap[no_of_frames];
        sprite_sheet=bitmap;
        for (int i=0;i<image.length;i++)
            image[i]=Bitmap.createBitmap(sprite_sheet,i*width,0,width,height);
        animation.setFrames(image);
        animation.setDelay(200-speed);
    }
    public void update()
    {
        x-=speed;
        animation.update();
    }
    public void draw(Canvas canvas)
    {
        try
        {
            canvas.drawBitmap(animation.get_image(),x,y,null);
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public int getWidth()
    {
        return width-10;
    }
}
