package com.example.mk141.imageslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class Custom_Aapter extends ArrayAdapter<String>
{
    public Custom_Aapter(Context context, String[] names)
    {
        super(context,R.layout.custom_layout, names);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater custom_inflater=LayoutInflater.from(getContext());
        View custom_view= custom_inflater.inflate(R.layout.custom_layout,parent,false);
        String single_item=getItem(position);
        TextView captain_marvel=(TextView)custom_view.findViewById(R.id.captain_marvel);
        ImageView imageView= (ImageView)custom_view.findViewById(R.id.imageView);
        captain_marvel.setText(single_item);
        imageView.setImageResource(R.drawable.smallcaptainmarvel);
        return custom_view;
    }
}
