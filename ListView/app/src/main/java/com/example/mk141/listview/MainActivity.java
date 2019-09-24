package com.example.mk141.listview;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RelativeLayout;
public class MainActivity extends Activity
{
    TextView textView;
    RelativeLayout relative_layout;
    ListView list_view;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relative_layout=(RelativeLayout)findViewById(R.id.relative_layout);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        String[] Names={"Ali","Zeesjan","Usman","Haider","Irfan","Bilal","Tahir","Salman","Mishal","sunny","shakeel","Awan","Ahmad",
        "Butt","ALCATRAZ","ATRAUS","KRATOS"};
        ListAdapter names_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Names);
        list_view=(ListView)findViewById(R.id.listView);
        list_view.setAdapter(names_adapter);
        list_view.setOnItemClickListener
        (
            new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    list_view.setVisibility(View.INVISIBLE);
                    String string=String.valueOf(adapterView.getItemAtPosition(i));
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("This is Profile of "+ string);
                    textView.setTextSize(30);
                    textView.setTextColor(Color.RED);
                    button.setVisibility(View.VISIBLE);
                }
            }
        );
    }
    public void back(View view)
    {
        list_view.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

    }
}