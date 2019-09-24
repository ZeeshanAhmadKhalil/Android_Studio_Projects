package com.example.mk141.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.app.Activity;
public class BottomPictureFragment extends Fragment{
    private TextView top_text;
    private TextView bottom_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_picture_fragment,container,false);
        top_text=(TextView)view.findViewById(R.id.top_text);
        bottom_text=(TextView)view.findViewById(R.id.bottom_text);
        return view;
    }
    public void setMemeText(String top,String bottom)
    {
        top_text.setText(top);
        bottom_text.setText(bottom);
    }
}
