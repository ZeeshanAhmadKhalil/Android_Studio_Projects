package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Obstecle extends Game_Object
{
    private int score;
    private int speed;
    private Bitmap sprited_sheet;
    private Random random=new Random();
    private Animation animation=new Animation();
    public Obstecle(Bitmap bitmap,int x,int y,int w,int h,int s,int no_of_frames)
    {
        super.x=x;
        super.y=y;
        width=w;
        height=h;
        score=s;
        speed=GamePanel.MOVE_SPEED;
        Bitmap[] image=new Bitmap[no_of_frames];
        sprited_sheet=bitmap;
        for (int i=0;i<image.length;i++)
            image[i]=Bitmap.createBitmap(sprited_sheet,i*width,0,width,height);
        animation.setFrames(image);
        animation.setDelay(100-speed);
    }
    public void update()
    {
        x+=speed;
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
