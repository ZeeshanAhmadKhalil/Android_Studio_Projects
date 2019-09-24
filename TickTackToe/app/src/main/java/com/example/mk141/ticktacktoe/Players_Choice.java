package com.example.mk141.ticktacktoe;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Players_Choice extends AppCompatActivity
{
    RadioButton player1_cross;
    RadioButton player2_cross;
    RadioButton player1_circle;
    RadioButton player2_circle;
    TextView player2_name;
    TextView player1_name;
    String player1;
    String player2;
    boolean player1_cross_checked=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players__choice);
        player1_cross=(RadioButton)findViewById(R.id.player1_cross);
        player2_cross=(RadioButton)findViewById(R.id.player2_cross);
        player1_circle=(RadioButton)findViewById(R.id.player1_circle);
        player2_circle=(RadioButton)findViewById(R.id.player2_circle);
        player1_name=(TextView)findViewById(R.id.player1_name_choice);
        player2_name=(TextView)findViewById(R.id.player2_name_choice);
        Bundle names=getIntent().getExtras();
        if(names==null)
            return;
        player1_name.setText(names.getString("player1_name")+"'s Choice:");
        player2_name.setText(names.getString("player2_name")+"'s Choice:");
        player1=names.getString("player1_name");
        player2=names.getString("player2_name");
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
        Intent player_names_activity=new Intent(this,Players_Names.class);
        startActivity(player_names_activity);
        return true;
    }
    public void player1_chooses(View view)
    {
        if(player1_circle.isChecked())
        {
            player2_cross.setChecked(true);
            player1_cross_checked=false;
        }
        else
        {
            player2_circle.setChecked(true);
            player1_cross_checked=true;
        }
    }
    public void player2_chooses(View view)
    {
        if(player2_circle.isChecked())
        {
            player1_cross.setChecked(true);
            player1_cross_checked=true;
        }
        else
        {
            player1_circle.setChecked(true);
            player1_cross_checked=false;
        }
    }
    public void to_game_started_activity(View view)
    {
        Intent game_started_activity=new Intent(this,Game_Started.class);
        game_started_activity.putExtra("player1_cross_checked",player1_cross_checked);
        game_started_activity.putExtra("player1",player1);
        game_started_activity.putExtra("player2",player2);
        Toast.makeText(getBaseContext(),player1+" will start first",Toast.LENGTH_LONG).show();
        startActivity(game_started_activity);
    }
}