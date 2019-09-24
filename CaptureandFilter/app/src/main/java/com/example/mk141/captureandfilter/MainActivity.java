package com.example.mk141.captureandfilter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.graphics.Matrix;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE=1;
    private static int RESULT_LOAD_IMG=1;
    ImageView imageView;
    Button capture_button,upload_button,save_button,filter_button,new_photo_btn,save_button2;
    EditText image_name;
    TextView textView;
    ListView edits_list,flip_or_rotate_list,frames_or_pngs_list,filters_list,color_detect_list;
    Bitmap bitmap,before_combining,before_filtering;
    Drawable original_image,rotated_original_image,heart,bubbles,frame,smoke,brokenglass;
    boolean is_combined,is_filtered,flipped_vertically,flipped_horizontally,is_captured;
    int rotated_right;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        imageView=findViewById(R.id.imageView);
        capture_button=findViewById(R.id.take_photo_btn);
        save_button=findViewById(R.id.save_button);
        upload_button=findViewById(R.id.upload_button);
        filter_button=findViewById(R.id.filter_button);
        new_photo_btn=findViewById(R.id.new_capture_button);
        save_button2=findViewById(R.id.save_button2);
        textView=findViewById(R.id.textView);
        image_name=findViewById(R.id.image_name);
        edits_list=findViewById(R.id.edits_list);
        flip_or_rotate_list=findViewById(R.id.flip_or_rotate_list);
        frames_or_pngs_list=findViewById(R.id.frames_and_pngs_list);
        filters_list=findViewById(R.id.filters_list);
        color_detect_list=findViewById(R.id.color_detect_list);
        heart=getResources().getDrawable(R.drawable.heartresized);
        frame=getResources().getDrawable(R.drawable.framechinese);
        bubbles=getResources().getDrawable(R.drawable.bubblesresized);
        smoke=getResources().getDrawable(R.drawable.smokeresized);
        brokenglass=getResources().getDrawable(R.drawable.brokenglassresized);
        final String[] edits={"Flip or Rotate","Frames or PNGs","Filters","Detect Color","Remove All Edits","Go Back"};
        final String[] flip_or_rotate={"Flip Horizontally","Flip Vertically","Rotate Left","Rotate Right","Remove Rotation","Go Back"};
        final String[] frames_or_pngs={"Hearts","Bubbles","Frame","Smoke","Broken Glass","Remove PNGs","Go Back"};
        final String[] filters={"Negative","Gray","Black & White","Remove Filters","Go Back"};
        final String[] colors_to_detect={"Blue","Green","Red","Black","White","Yellow","Magenta","Silver","Remove Color Detects","Go Back"};
        ListAdapter edits_adapter=new Custom_Adapter(this,edits);
        edits_list.setAdapter(edits_adapter);
        ListAdapter flip_or_rotate_adapter=new Custom_Adapter(this,flip_or_rotate);
        flip_or_rotate_list.setAdapter(flip_or_rotate_adapter);
        ListAdapter frames_or_pngs_adapter=new Custom_Adapter(this,frames_or_pngs);
        frames_or_pngs_list.setAdapter(frames_or_pngs_adapter);
        ListAdapter filters_adapter=new Custom_Adapter(this,filters);
        filters_list.setAdapter(filters_adapter);
        final ListAdapter color_detect_adapter=new Custom_Adapter(this,colors_to_detect);
        color_detect_list.setAdapter(color_detect_adapter);
        color_detect_list.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                color_detect_list.setVisibility(View.INVISIBLE);
                                int marker_width;
                                if(bitmap.getWidth()*bitmap.getHeight()>250000)
                                    marker_width=5;
                                else
                                    marker_width=2;
                                if(i==0)
                                {
                                    detect_color(0,0,255,180,marker_width,255,255,0);
                                }
                                else if(i==1)
                                {
                                    detect_color(0,255,0,180,marker_width,255,0,0);
                                }
                                else if(i==2)
                                {
                                    detect_color(255,0,0,80,marker_width,255,255,0);
                                }
                                else if(i==3)
                                {
                                    detect_color(0,0,0,100,marker_width,255,255,0);
                                }
                                else if(i==4)
                                {
                                    detect_color(255,255,255,90,marker_width,255,0,0);
                                }
                                else if(i==5)
                                {
                                    detect_color(255,255,0,150,marker_width,255,0,0);
                                }
                                else if(i==6)
                                {
                                    detect_color(255,0,255,150,marker_width,255,0,0);
                                }
                                else if(i==7)
                                {
                                    detect_color(192,192,192,100,marker_width,255,0,0);
                                }
                                else if (i==8)
                                {
                                    imageView.setImageBitmap(bitmap);
                                    buttons_and_image_view_visibility(true);
                                }
                                else if(i==9)
                                {
                                    edits_list.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );
        edits_list.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                edits_list.setVisibility(View.INVISIBLE);
                                if(i==0)
                                {
                                    flip_or_rotate_list.setVisibility(View.VISIBLE);
                                }
                                else if(i==1)
                                {
                                    frames_or_pngs_list.setVisibility(View.VISIBLE);
                                }
                                else if(i==2)
                                {
                                    filters_list.setVisibility(View.VISIBLE);
                                }
                                else if(i==3)
                                {
                                    color_detect_list.setVisibility(View.VISIBLE);
                                }
                                else if(i==4)
                                {
                                    rotated_original_image=original_image;
                                    bitmap =((BitmapDrawable)original_image).getBitmap();
                                    imageView.setImageBitmap(bitmap);
                                    flipped_horizontally=flipped_vertically=is_combined=is_filtered=false;
                                    rotated_right=0;
                                    buttons_and_image_view_visibility(true);
                                }
                                else
                                {
                                    buttons_and_image_view_visibility(true);
                                }
                            }
                        }
                );
        flip_or_rotate_list.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                flip_or_rotate_list.setVisibility(View.INVISIBLE);
                                
                                if(i<=4)
                                {
                                    if(i==0)
                                    {
                                        bitmap =invert(true, bitmap);
                                        if(flipped_horizontally)
                                            flipped_horizontally=false;
                                        else
                                            flipped_horizontally=true;
                                        if(is_filtered)
                                            before_filtering=invert(true,before_filtering);
                                        if(is_combined)
                                            before_combining=invert(true,before_combining);
                                    }
                                    else if(i==1)
                                    {
                                        bitmap =invert(false, bitmap);
                                        if(flipped_vertically)
                                            flipped_vertically=false;
                                        else
                                            flipped_vertically=true;
                                        if(is_filtered)
                                            before_filtering=invert(false,before_filtering);
                                        if(is_combined)
                                            before_combining=invert(false,before_combining);
                                    }
                                    else if(i==2)
                                    {
                                        bitmap =rotate(false, bitmap);
                                        if(rotated_right==0)
                                            rotated_right=3;
                                        else
                                            rotated_right--;
                                        if(is_filtered)
                                            before_filtering=rotate(false,before_filtering);
                                        if(is_combined)
                                            before_combining=rotate(false,before_combining);
                                    }
                                    else if(i==3)
                                    {
                                        bitmap =rotate(true, bitmap);
                                        if(rotated_right==3)
                                            rotated_right=0;
                                        else
                                            rotated_right++;
                                        if(is_filtered)
                                            before_filtering=rotate(true,before_filtering);
                                        if(is_combined)
                                            before_combining=rotate(true,before_combining);
                                    }
                                    else
                                    {
                                        if(flipped_vertically)
                                        {
                                            flipped_vertically=false;
                                            bitmap =invert(false, bitmap);
                                        }
                                        if(flipped_horizontally)
                                        {
                                            flipped_horizontally=false;
                                            bitmap =invert(true, bitmap);
                                        }
//                                            invert(true,bitmap);
                                        while(rotated_right!=0)
                                        {
                                            bitmap =rotate(false, bitmap);
                                            rotated_right--;
                                        }
                                    }
                                    imageView.setImageBitmap(bitmap);
                                    buttons_and_image_view_visibility(true);
                                }
                                else
                                {
                                    edits_list.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );
        frames_or_pngs_list.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                frames_or_pngs_list.setVisibility(View.INVISIBLE);
                                if(i<=5)
                                {
                                    if(!is_combined)
                                    {
                                        before_combining= bitmap;
                                        is_combined=true;
                                    }
                                    else
                                        bitmap =before_combining;
                                    Drawable drawable=new BitmapDrawable(getResources(), bitmap),layerdrawable = null;
                                    if(i==0)
                                    {
                                        if(is_filtered)
                                        {
                                            layerdrawable=combine(rotated_original_image,heart);
                                            before_filtering=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                            Canvas canvas=new Canvas(before_filtering);
                                            layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                            layerdrawable.draw(canvas);
                                        }
                                        layerdrawable=combine(drawable,heart);
                                    }
                                    else if(i==1)
                                    {
                                        if(is_filtered)
                                        {
                                            layerdrawable=combine(rotated_original_image,bubbles);
                                            before_filtering=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                            Canvas canvas=new Canvas(before_filtering);
                                            layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                            layerdrawable.draw(canvas);
                                        }
                                        layerdrawable=combine(drawable,bubbles);
                                    }
                                    else if(i==2)
                                    {
                                        if(is_filtered)
                                        {
                                            layerdrawable=combine(rotated_original_image,frame);
                                            before_filtering=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                            Canvas canvas=new Canvas(before_filtering);
                                            layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                            layerdrawable.draw(canvas);
                                        }
                                        layerdrawable=combine(drawable,frame);
                                    }
                                    else if(i==3)
                                    {
                                        if(is_filtered)
                                        {
                                            layerdrawable=combine(rotated_original_image,smoke);
                                            before_filtering=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                            Canvas canvas=new Canvas(before_filtering);
                                            layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                            layerdrawable.draw(canvas);
                                        }
                                        layerdrawable=combine(drawable,smoke);
                                    }
                                    else if(i==4)
                                    {
                                        if(is_filtered)
                                        {
                                            layerdrawable=combine(rotated_original_image,brokenglass);
                                            before_filtering=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                            Canvas canvas=new Canvas(before_filtering);
                                            layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                            layerdrawable.draw(canvas);
                                        }
                                        layerdrawable=combine(drawable,brokenglass);
                                    }
                                    else
                                    {
                                        if(is_combined)
                                        {
                                            if(is_filtered)
                                            {
                                                before_filtering=((BitmapDrawable)rotated_original_image).getBitmap();
                                            }
                                            layerdrawable=new BitmapDrawable(getResources(),before_combining);
                                            is_combined=false;
                                        }
                                    }
                                    bitmap =Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                                    Canvas canvas=new Canvas(bitmap);
                                    layerdrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
                                    layerdrawable.draw(canvas);
                                    imageView.setImageBitmap(bitmap);
                                    buttons_and_image_view_visibility(true);
                                }
                                else
                                {
                                    edits_list.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );
        filters_list.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                filters_list.setVisibility(View.INVISIBLE);
                                if(i<=3)
                                {
                                    Bitmap temp_bitmap;
                                    if(!is_filtered)
                                    {
                                        before_filtering= bitmap;
                                        is_filtered=true;
                                    }
                                    else
                                        bitmap =before_filtering;
                                    if(i==0)
                                    {
                                        if(is_combined)
                                        {
                                            temp_bitmap=((BitmapDrawable)rotated_original_image).getBitmap();
                                            before_combining=negative(temp_bitmap);
                                        }
                                        bitmap =negative(bitmap);
                                    }
                                    else if(i==1)
                                    {
                                        if(is_combined)
                                        {
                                            temp_bitmap=((BitmapDrawable)rotated_original_image).getBitmap();
                                            before_combining=rgb2gray(temp_bitmap);
                                        }
                                        bitmap =rgb2gray(bitmap);
                                    }
                                    else if(i==2)
                                    {
                                        if(is_combined)
                                        {
                                            temp_bitmap=((BitmapDrawable)rotated_original_image).getBitmap();
                                            before_combining=rgb2bw(temp_bitmap);
                                        }
                                        bitmap =rgb2bw(bitmap);
                                    }
                                    else
                                    {
                                        if(is_filtered)
                                        {
                                            if(is_combined)
                                            {
                                                before_combining=((BitmapDrawable)rotated_original_image).getBitmap();
                                            }
                                            bitmap =before_filtering;
                                            is_filtered=false;
                                        }
                                    }
                                    imageView.setImageBitmap(bitmap);
                                    buttons_and_image_view_visibility(true);
                                }
                                else
                                {
                                    edits_list.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                );
        if(!has_camera())
        {
            capture_button.setEnabled(false);
        }
    }
    public void detect_color(int r,int g,int b,int threashold,int marker_width,int marker_r,int marker_g,int marker_b)
    {
        Bitmap new_bitmap=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int pixel;
        double eculedian_distance,R,G,B,eculedian_distance_next=0,eculedian_distance_pre=0,eculedian_distance_top=0,eculedian_distance_bottom=0;
        for(int i = 0; i< new_bitmap.getWidth(); i++)
        {
            for(int j = 0; j<new_bitmap.getHeight(); j++)
            {
                pixel= bitmap.getPixel(i,j);
                R=Color.red(pixel);
                G=Color.green(pixel);
                B=Color.blue(pixel);
                eculedian_distance=Math.sqrt((R-r)*(R-r)+(G-g)*(G-g)+(B-b)*(B-b));
                if(i+marker_width<bitmap.getWidth())
                {
                    pixel= bitmap.getPixel(i+marker_width,j);
                    R=Color.red(pixel);
                    G=Color.green(pixel);
                    B=Color.blue(pixel);
                    eculedian_distance_next=Math.sqrt((R-r)*(R-r)+(G-g)*(G-g)+(B-b)*(B-b));
                }
                if(i-marker_width>=0)
                {
                    pixel= bitmap.getPixel(i-marker_width,j);
                    R=Color.red(pixel);
                    G=Color.green(pixel);
                    B=Color.blue(pixel);
                    eculedian_distance_pre=Math.sqrt((R-r)*(R-r)+(G-g)*(G-g)+(B-b)*(B-b));
                }
                if(j+marker_width<bitmap.getHeight())
                {
                    pixel= bitmap.getPixel(i,j+marker_width);
                    R=Color.red(pixel);
                    G=Color.green(pixel);
                    B=Color.blue(pixel);
                    eculedian_distance_bottom=Math.sqrt((R-r)*(R-r)+(G-g)*(G-g)+(B-b)*(B-b));
                }
                if(j-marker_width>=0)
                {
                    pixel= bitmap.getPixel(i,j-marker_width);
                    R=Color.red(pixel);
                    G=Color.green(pixel);
                    B=Color.blue(pixel);
                    eculedian_distance_top=Math.sqrt((R-r)*(R-r)+(G-g)*(G-g)+(B-b)*(B-b));
                }
                if((eculedian_distance<=threashold&&eculedian_distance_next>threashold)||((eculedian_distance<=threashold&&eculedian_distance_pre>threashold))||(eculedian_distance<=threashold&&eculedian_distance_top>threashold)||((eculedian_distance<=threashold&&eculedian_distance_bottom>threashold))||(eculedian_distance<=threashold&&j<marker_width)||(eculedian_distance<=threashold&&j>bitmap.getWidth()-marker_width)||(eculedian_distance<=threashold&&i<marker_width)||(eculedian_distance<=threashold&&j>bitmap.getHeight()-marker_width))
                    new_bitmap.setPixel(i,j,Color.argb(255,marker_r,marker_g,marker_b));
                else
                    new_bitmap.setPixel(i,j,Color.argb(0,255,255,255));
            }
        }
        Bitmap combined_bitmap;
        Drawable drawable1=new BitmapDrawable(getResources(),bitmap);
        Drawable drawable2=new BitmapDrawable(getResources(),new_bitmap);
        LayerDrawable layerDrawable=combine(drawable1,drawable2);
        combined_bitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        Canvas canvas=new Canvas(combined_bitmap);
        layerDrawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        layerDrawable.draw(canvas);
        imageView.setImageBitmap(combined_bitmap);
        buttons_and_image_view_visibility(true);
    }
    public void print_toast(String a)
    {
        Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
    }
    public void open_camera(View view)
    {
        is_captured=true;
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }
    public void upload_image(View view)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK&&is_captured)
        {
            try
            {
                final Uri imageUri=data.getData();
                final InputStream imageStream=getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(imageStream);
                bitmap =Bitmap.createScaledBitmap(bitmap,1280,720,true);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                Toast.makeText(this,"Something went wrong ",Toast.LENGTH_SHORT).show();
            }
        }
        else if(resultCode==RESULT_OK)
        {
            try
            {
                final Uri imageUri=data.getData();
                final InputStream imageStream=getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(imageStream);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                Toast.makeText(this,"Something went wrong ",Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(this,"No image selected ",Toast.LENGTH_SHORT).show();
        buttons_and_image_view_visibility(true);
        imageView.setImageBitmap(bitmap);
        rotated_original_image=original_image=imageView.getDrawable();
        is_captured=is_combined=is_filtered=flipped_horizontally=flipped_vertically=false;
        rotated_right=0;
    }

    public boolean has_camera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void show_filters(View view)
    {
        buttons_and_image_view_visibility(false);
        edits_list.setVisibility(View.VISIBLE);

    }
    public void save_photo(View view)
    {
        buttons_and_image_view_visibility(false);
        save_views_visibility(true);
    }
    public void save_photo_process(View view)
    {
        File path;
        if(Environment.getExternalStorageState()!=null)
            path=Environment.getExternalStorageDirectory();
        else
            path=Environment.getRootDirectory();
        File dir=new File(path+"/Capture and Edit/");
        File file=new File(dir,image_name.getText().toString()+".png");
        OutputStream out;
        try
        {
            out=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.flush();
            out.close();
        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
        }
        buttons_and_image_view_visibility(true);
        save_views_visibility(false);
        Toast.makeText(MainActivity.this,image_name.getText().toString()+" is saved to "+path+"/Capture and Edit/",Toast.LENGTH_LONG).show();
    }
    public void buttons_and_image_view_visibility(boolean b)
    {
        if(b)
        {
            capture_button.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            new_photo_btn.setVisibility(View.VISIBLE);
            filter_button.setVisibility(View.VISIBLE);
            save_button.setVisibility(View.VISIBLE);
            upload_button.setVisibility(View.INVISIBLE);

        }
        else
        {
            imageView.setVisibility(View.INVISIBLE);
            new_photo_btn.setVisibility(View.INVISIBLE);
            filter_button.setVisibility(View.INVISIBLE);
            save_button.setVisibility(View.INVISIBLE);
        }
    }
    public void save_views_visibility(boolean b)
    {
        if(b)
        {
            textView.setVisibility(View.VISIBLE);
            save_button2.setVisibility(View.VISIBLE);
            image_name.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setVisibility(View.INVISIBLE);
            save_button2.setVisibility(View.INVISIBLE);
            image_name.setVisibility(View.INVISIBLE);
        }
    }
    public void start_visible(View view)
    {
        buttons_and_image_view_visibility(false);
        capture_button.setVisibility(View.VISIBLE);
        upload_button.setVisibility(View.VISIBLE);
    }
    public LayerDrawable combine(Drawable drawable1,Drawable drawable2)
    {
        Bitmap temp_bitmap;
        temp_bitmap=Bitmap.createScaledBitmap(((BitmapDrawable)drawable2).getBitmap(),480,640,true);
        drawable2=new BitmapDrawable(getResources(),temp_bitmap);
        Drawable[] drawables=new Drawable[2];
        drawables[0]=drawable1;
        drawables[1]=drawable2;
        LayerDrawable layerDrawable=new LayerDrawable(drawables);
        return layerDrawable;
    }
    public Bitmap negative(Bitmap bitmap)
    {
        float[] negative_colour_matrix={-1, 0, 0, 0,255,
                                         0,-1, 0, 0,255,
                                         0, 0,-1, 0,255,
                                         0, 0, 0,1,0};
        ColorMatrix colorMatrix=new ColorMatrix(negative_colour_matrix);
        return get_bitmap_from_color_matrix(colorMatrix,bitmap);
    }
    public Bitmap invert(boolean horizontally,Bitmap bitmap)
    {
        Matrix matrix=new Matrix();
        if(horizontally)
            matrix.preScale(-1,1);
        else
            matrix.preScale(1,-1);

        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }
    public Bitmap rgb2gray(Bitmap bitmap)
    {
        float[] gray_colour_matrix={0.21f,0.72f,0.07f,0,0,
                                    0.21f,0.72f,0.07f,0,0,
                                    0.21f,0.72f,0.07f,0,0,
                                    0,    0,    0,    1,0};
        ColorMatrix colorMatrix=new ColorMatrix(gray_colour_matrix);
        return get_bitmap_from_color_matrix(colorMatrix,bitmap);
    }
    public Bitmap rgb2bw(Bitmap bitmap)
    {
        float[] gray_colour_matrix={0.21f*255,0.72f*255,0.07f*255,0,-255*127,
                                    0.21f*255,0.72f*255,0.07f*255,0,-255*127,
                                    0.21f*255,0.72f*255,0.07f*255,0,-255*127,
                                    0,    0,    0,    1,0};
        ColorMatrix colorMatrix=new ColorMatrix(gray_colour_matrix);
        return get_bitmap_from_color_matrix(colorMatrix,bitmap);
    }
    public Bitmap rotate(boolean right,Bitmap bitmap)
    {
        Matrix matrix = new Matrix();
        if(right)
            matrix.postRotate(90);
        else
            matrix.postRotate(270);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }
    public Bitmap get_bitmap_from_color_matrix(ColorMatrix colorMatrix,Bitmap bitmap)
    {
        Bitmap ret=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        Canvas canvas=new Canvas(ret);
        Paint paint=new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        return ret;
    }
}