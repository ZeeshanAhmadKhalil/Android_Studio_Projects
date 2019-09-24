package com.example.mk141.overflowmenu1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.Color;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        RelativeLayout main_layout=(RelativeLayout)findViewById(R.id.main_layout);
        TextView colour=(TextView)findViewById(R.id.colour);
        if(item.getItemId()==R.id.red)
        {
            if(item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
            main_layout.setBackgroundColor(Color.RED);
            colour.setText("Red");
            return true;
        }
        else if(item.getItemId()==R.id.green)
        {
            if(item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
            main_layout.setBackgroundColor(Color.GREEN);
            colour.setText("Green");
            return true;
        }
        else if(item.getItemId()==R.id.blue)
        {
            if(item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
            main_layout.setBackgroundColor(Color.BLUE);
            colour.setText("Blue");
            return true;
        }
        else
        return super.onOptionsItemSelected(item);
    }
}
