package com.example.mk141.imagefilter;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    String TAG="com.example.mk141.imagefilter";
    ImageView imageView;
    Drawable buckey_family;
    Drawable heart;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        buckey_family=getResources().getDrawable(R.drawable.buckeyfamilyresized);
        heart=getResources().getDrawable(R.drawable.heartresized);
        bitmap=((BitmapDrawable)buckey_family).getBitmap();
        imageView.setImageDrawable(buckey_family);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(!item.isChecked())
            item.setChecked(true);
        if(item.getItemId()==R.id.original)
        {
            bitmap=((BitmapDrawable)buckey_family).getBitmap();
            imageView.setImageDrawable(buckey_family);
        }
        else if(item.getItemId()==R.id.heart)
        {
            Drawable new_family=new BitmapDrawable(getResources(),bitmap);
            imageView.setImageDrawable(combine_image_with_png(new_family,heart));
        }
        else if(item.getItemId()==R.id.negative)
        {
            imageView.setImageBitmap(negative(bitmap));
        }
        else if(item.getItemId()==R.id.invert)
        {
            bitmap=invert(bitmap);
            imageView.setImageBitmap(bitmap);
        }
        else if(item.getItemId()==R.id.gray)
        {
            imageView.setImageBitmap(rgb2gray(bitmap));
        }
        else if(item.getItemId()==R.id.bw)
        {
            imageView.setImageBitmap(rgb2bw(bitmap));
        }
        return true;
    }

    public Bitmap negative(Bitmap bitmap)
    {
        int A,R,G,B;
        int pixel_colour;
        Bitmap negative_image=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        for(int x=0;x<bitmap.getWidth();x++)
        {
            for(int y=0;y<bitmap.getHeight();y++)
            {
                pixel_colour=bitmap.getPixel(x,y);
                A= Color.alpha(pixel_colour);
                R=255-Color.red(pixel_colour);
                G=255-Color.green(pixel_colour);
                B=255-Color.blue(pixel_colour);
                negative_image.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return negative_image;
    }
    public Bitmap invert(Bitmap bitmap)
    {
        int pixel_colour;
        Bitmap inverted_image=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        int X=bitmap.getWidth()-1;
        for(int x=bitmap.getWidth()-1;x>=0;x--)
        {
            for(int y=0;y<bitmap.getHeight();y++)
            {
                pixel_colour=bitmap.getPixel(x,y);
                inverted_image.setPixel(X-x,y,pixel_colour);
            }
        }
        return inverted_image;
    }
    public Bitmap rgb2gray(Bitmap bitmap)
    {
        int A,R,G,B;
        int pixel_colour;
        Bitmap grayscale_image=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        for(int x=0;x<bitmap.getWidth();x++)
        {
            for(int y=0;y<bitmap.getHeight();y++)
            {
                pixel_colour=bitmap.getPixel(x,y);
                A=Color.alpha(pixel_colour);
                R=Color.red(pixel_colour);
                G=Color.green(pixel_colour);
                B=Color.blue(pixel_colour);
                R=G=B=(int)(0.21*R + 0.72*G + 0.07*B);
                grayscale_image.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return grayscale_image;
    }
    public Bitmap rgb2bw(Bitmap bitmap)
    {
        Bitmap gray_image=rgb2gray(bitmap);
        int A,R,G,B;
        int pixel_colour;
        Bitmap bw_image=Bitmap.createBitmap(gray_image.getWidth(),gray_image.getHeight(),gray_image.getConfig());
        for(int x=0;x<bitmap.getWidth();x++)
        {
            for(int y=0;y<bitmap.getHeight();y++)
            {
                pixel_colour=gray_image.getPixel(x,y);
                A=Color.alpha(pixel_colour);
                R=Color.red(pixel_colour);
                if(R>=128)
                    R=G=B=255;
                else
                    R=G=B=0;
                bw_image.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return bw_image;
    }
    public LayerDrawable combine_image_with_png(Drawable d1,Drawable d2)
    {
        Drawable[] drawables=new Drawable[2];
        drawables[0]=d1;
        drawables[1]=d2;
        LayerDrawable layerDrawable=new LayerDrawable(drawables);
        return layerDrawable;
    }
    public void save_image(View view)
    {
//        MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Buckey's Family","description");
        String path=savePicture(bitmap,"buckey.jpg");
        Toast.makeText(MainActivity.this,"Image is saved to "+path,Toast.LENGTH_SHORT).show();

    }
    private String savePicture(Bitmap bitmapImage, String imgName)
    {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,imgName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
        }
    }
