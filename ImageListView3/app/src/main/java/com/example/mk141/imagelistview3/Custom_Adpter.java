package com.example.mk141.imagelistview3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_Adpter extends ArrayAdapter<String>
{
    public Custom_Adpter(Context context,String[] heroes)
    {
        super(context,R.layout.custom_row,heroes);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater custom_inflater=LayoutInflater.from(getContext());
        View custom_view=custom_inflater.inflate(R.layout.custom_row,parent,false);
        String single_name=getItem(position);
        TextView name=custom_view.findViewById(R.id.name);
        ImageView image=custom_view.findViewById(R.id.image);
        name.setText(single_name);
        if(single_name=="Captain Marvel")
            image.setImageResource(R.drawable.smallcaptainmarvel);
        else if(single_name=="Iron Man")
            image.setImageResource(R.drawable.smallironman);
        else if(single_name=="Captain America")
            image.setImageResource(R.drawable.smallcaptainamerica);
        else if(single_name=="Thor")
            image.setImageResource(R.drawable.smallthor);
        else if(single_name=="Star Lord")
            image.setImageResource(R.drawable.smallstarlord);
        else if(single_name=="Thanos")
            image.setImageResource(R.drawable.smallthanose);
        else if(single_name=="Spider Man")
            image.setImageResource(R.drawable.smallspiderman);
        else if(single_name=="Falcon")
            image.setImageResource(R.drawable.smallfalcon);
        else if(single_name=="Doctor Strange")
            image.setImageResource(R.drawable.smalldrstrange);
        else if(single_name=="Black Panther")
            image.setImageResource(R.drawable.smallblackpanther);

        return custom_view;

    }
}
