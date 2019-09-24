package com.example.mk141.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
public class TopSectionFragment extends Fragment
{
    private EditText top_text_input;
    private EditText bottom_text_input;
    TopSectionListener activity_commander;
    public interface TopSectionListener
    {
        void createMeme(String top,String bottom);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            activity_commander=(TopSectionListener)activity;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.top_section_fragment,container,false);
        top_text_input=(EditText)view.findViewById(R.id.top_text_input);
        bottom_text_input=(EditText)view.findViewById(R.id.bottom_text_input);
        final Button button1=(Button)view.findViewById(R.id.button1);
        button1.setOnClickListener
         (
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        buttonClicked(v);
                    }
                }
         );
        return view;
    }
    public void buttonClicked(View v)
    {
        activity_commander.createMeme(top_text_input.getText().toString(),bottom_text_input.getText().toString());
    }
}
