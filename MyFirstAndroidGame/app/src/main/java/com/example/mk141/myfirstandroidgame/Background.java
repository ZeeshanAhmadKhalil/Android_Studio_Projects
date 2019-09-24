package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background
{
    private Bitmap image;
    private int x,y,dx;

    public Background(Bitmap bitmap)
    {
        image=bitmap;
        dx=GamePanel.MOVE_SPEED;
    }
    public void update()
    {
        x+=dx;
        if (x<-GamePanel.WIDTH)
            x=0;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image,x,y,null);
        if (x<0)
            canvas.drawBitmap(image,x+GamePanel.WIDTH,y,null);
    }
//    public void setVector(int dx)
//    {
//        this.dx=dx;
//    }
}
