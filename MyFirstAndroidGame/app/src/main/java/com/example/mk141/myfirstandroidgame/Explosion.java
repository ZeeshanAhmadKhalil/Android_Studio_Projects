package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Explosion
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int row;
    private Animation animation=new Animation();
    private Bitmap sprite_sheet;
    public Explosion(Bitmap bitmap,int x,int y,int w,int h,int no_of_frames)
    {
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        Bitmap[] image=new Bitmap[no_of_frames];
        sprite_sheet=bitmap;
        for (int i=0;i<image.length;i++)
        {
            if(i%5==0&&i>0)
                row++;
            image[i]=Bitmap.createBitmap(sprite_sheet,(i-(5*row))*width,row*height,width,height);
        }
        animation.setFrames(image);
        animation.setDelay(10);
    }
    public void update()
    {
        if (!animation.get_palyed_once())
            animation.update();
    }
    public void draw(Canvas canvas)
    {
        if (!animation.get_palyed_once())
            canvas.drawBitmap(animation.get_image(),x,y,null);
    }
}
