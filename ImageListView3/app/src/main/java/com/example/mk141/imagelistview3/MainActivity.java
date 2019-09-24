package com.example.mk141.imagelistview3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView profile_text;
    Button back_button;
    ListView heroes_list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_text=findViewById(R.id.profile_text);
        back_button=findViewById(R.id.back_button);
        String[] heroes={"Captain Marvel","Iron Man","Captain America","Black Panther","Doctor Strange","Falcon","Spider Man","Thanos"
        ,"Star Lord","Thor"};
        ListAdapter heroes_adapter=new Custom_Adpter(this,heroes);
        heroes_list_view=(ListView)findViewById(R.id.list_view);
        heroes_list_view.setAdapter(heroes_adapter);
        heroes_list_view.setOnItemClickListener
                (
                        new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                String heroes_name=String.valueOf(adapterView.getItemAtPosition(i));
                                heroes_list_view.setVisibility(View.INVISIBLE);
                                profile_text.setVisibility(View.VISIBLE);
                                profile_text.setText("This is Profile of "+heroes_name);
                                back_button.setVisibility(View.VISIBLE);
                            }
                        }
                );
    }
    public void back(View view)
    {
        back_button.setVisibility(View.INVISIBLE);
        profile_text.setVisibility(View.INVISIBLE);
        heroes_list_view.setVisibility(View.VISIBLE);
    }
}