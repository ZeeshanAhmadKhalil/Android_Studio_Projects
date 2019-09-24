package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet extends Game_Object
{
    private int speed;
    private Animation animation=new Animation();
    private Bitmap spread_sheet;
    public Bullet(Bitmap bitmap,int x,int y,int w,int h,int no_of_frames)
    {
        super.x=x;
        super.y=y;
        width=w;
        height=h;
        speed=13;
        Bitmap[] image=new Bitmap[no_of_frames];
        spread_sheet=bitmap;
        for (int i=0;i<no_of_frames;i++)
            image[i]=Bitmap.createBitmap(bitmap,0,i*height,width,height);
        animation.setFrames(image);
        animation.setDelay(120-speed);
    }
    public void update()
    {
        x+=speed-4;
        animation.update();
    }
    public void draw(Canvas canvas)
    {
        try
        {
            canvas.drawBitmap(animation.get_image(),x-30,y,null);
        }
        catch (Exception e)
        {

        }
    }
}
