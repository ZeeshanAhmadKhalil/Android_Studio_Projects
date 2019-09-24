package com.example.mk141.moonscamera3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE=1;//variable name can't be changed
    Button capture_button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        capture_button=findViewById(R.id.capture_button);
        imageView=findViewById(R.id.imageView);
        capture_button.setOnLongClickListener
        (
            new Button.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    capture_button.setBackgroundColor(Color.RED);
                    return false;
                }
            }
        );
        if(!has_camera())
        {
            capture_button.setEnabled(false);
            capture_button.setText("No Camera");
        }
    }
    public void take_photo(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        capture_button.setBackgroundColor(Color.BLACK);
    }
    public boolean has_camera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK);
        {
            Bundle extras=data.getExtras();
            Bitmap photo=(Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
