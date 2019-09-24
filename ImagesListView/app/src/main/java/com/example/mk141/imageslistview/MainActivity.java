package com.example.mk141.imageslistview;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] marvel_heroes={"Captain Marvel","Iron Man","Captain America","Black Widow","Black Panther","Spider Man","Hulk","Falcon"};
        ListAdapter listAdapter=new Custom_Aapter(this,marvel_heroes);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener
        (
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        String name= String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(MainActivity.this, "You Clicked "+name, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}