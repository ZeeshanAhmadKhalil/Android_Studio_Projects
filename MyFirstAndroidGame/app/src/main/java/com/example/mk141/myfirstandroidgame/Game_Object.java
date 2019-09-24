package com.example.mk141.myfirstandroidgame;

import android.graphics.Rect;

public abstract class Game_Object
{
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int height;
    protected int width;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
    public Rect getRextangle()
    {
        return new Rect(x,y,x+width,y+height);
    }
}
