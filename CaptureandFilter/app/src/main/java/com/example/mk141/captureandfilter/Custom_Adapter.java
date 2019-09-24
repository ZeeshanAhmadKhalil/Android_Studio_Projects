package com.example.mk141.captureandfilter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_Adapter extends ArrayAdapter<String>
{
    public Custom_Adapter(Context context, String[] string)
    {
        super(context,R.layout.custom_row, string);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater custom_inflater=LayoutInflater.from(getContext());
        View custom_view=custom_inflater.inflate(R.layout.custom_row,parent,false);
        String single_list_item_text=getItem(position);
        TextView name=custom_view.findViewById(R.id.name);
        ImageView image=custom_view.findViewById(R.id.image);
        name.setText(single_list_item_text);
        if(single_list_item_text=="Flip or Rotate")
            image.setImageResource(R.drawable.fliporrotatebtn);
        else if(single_list_item_text=="Black & White")
            image.setImageResource(R.drawable.blackandwhitebtn);
        else if(single_list_item_text=="Frames or PNGs")
            image.setImageResource(R.drawable.framesorpngsbtn);
        else if(single_list_item_text=="Filters")
            image.setImageResource(R.drawable.filterbtn);
        else if(single_list_item_text=="Remove All Edits"||single_list_item_text=="Remove Rotation"||single_list_item_text=="Remove PNGs"||single_list_item_text=="Remove Filters"||single_list_item_text=="Remove Color Detects")
            image.setImageResource(R.drawable.removebtn);
        else if(single_list_item_text=="Go Back")
            image.setImageResource(R.drawable.backbtn);
        else if(single_list_item_text=="Flip Horizontally")
            image.setImageResource(R.drawable.fliphorizontallybtn);
        else if(single_list_item_text=="Flip Vertically")
            image.setImageResource(R.drawable.flipverticallybtn);
        else if(single_list_item_text=="Rotate Left")
            image.setImageResource(R.drawable.rotateleftbtn);
        else if(single_list_item_text=="Rotate Right")
            image.setImageResource(R.drawable.rotaterightbtn);
        else if(single_list_item_text=="Hearts")
            image.setImageResource(R.drawable.heartsbtn);
        else if(single_list_item_text=="Bubbles")
            image.setImageResource(R.drawable.bubblesbtn);
        else if(single_list_item_text=="Frame")
            image.setImageResource(R.drawable.framebtn);
        else if(single_list_item_text=="Smoke")
            image.setImageResource(R.drawable.smokebtn);
        else if(single_list_item_text=="Broken Glass")
            image.setImageResource(R.drawable.brokenglassbtn);
        else if(single_list_item_text=="Negative")
            image.setImageResource(R.drawable.negativebtn);
        else if(single_list_item_text=="Gray")
            image.setImageResource(R.drawable.graybtn);
        else if(single_list_item_text=="Detect Color")
            image.setImageResource(R.drawable.detect_color_btn);
        else if(single_list_item_text=="Blue")
            image.setImageResource(R.drawable.blue_btn);
        else if(single_list_item_text=="Green")
            image.setImageResource(R.drawable.green_btn);
        else if(single_list_item_text=="Red")
            image.setImageResource(R.drawable.red_btn);
        else if(single_list_item_text=="Black")
            image.setImageResource(R.drawable.black_btn);
        else if(single_list_item_text=="White")
            image.setImageResource(R.drawable.white_btn);
        else if(single_list_item_text=="Yellow")
            image.setImageResource(R.drawable.yellow_btn);
        else if(single_list_item_text=="Magenta")
            image.setImageResource(R.drawable.pink_btn);
        else if(single_list_item_text=="Silver")
            image.setImageResource(R.drawable.silver_btn);
        return custom_view;
    }
}
