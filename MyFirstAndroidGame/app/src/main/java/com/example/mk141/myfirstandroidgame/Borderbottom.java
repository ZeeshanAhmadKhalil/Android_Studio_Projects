package com.example.mk141.myfirstandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Borderbottom extends Game_Object
{
    private Bitmap image;
    public Borderbottom(Bitmap bitmap,int x,int y)
    {
        height=150;
        width=20;
        super.x=x;
        super.y=y;
        dx=GamePanel.MOVE_SPEED;
        image=Bitmap.createBitmap(bitmap,0,0,width,height);

    }
    public void update()
    {
        x+=dx;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image,x,y,null);
    }

    @Override
    public int getX()
    {
        return super.getX();
    }
}
