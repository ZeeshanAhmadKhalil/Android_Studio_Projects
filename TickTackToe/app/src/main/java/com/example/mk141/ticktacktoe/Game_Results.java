package com.example.mk141.ticktacktoe;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class Game_Results extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__results);
        TextView result_text=(TextView)findViewById(R.id.result_text);
        Bundle names_and_winner_info=getIntent().getExtras();
        String player1_name=names_and_winner_info.getString("player1");
        String player2_name=names_and_winner_info.getString("player2");
        int Counter=names_and_winner_info.getInt("Counter");
        boolean player1_winner=names_and_winner_info.getBoolean("player1_winner");
        if(Counter==9)
        {
            result_text.setText("The Game is Draw");
        }
        else
        {
            if(player1_winner)
                result_text.setText(player1_name+" is the winner");
            else
                result_text.setText(player2_name+" is the winner");
        }
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
    public void onRestart(View view)
    {
        Intent player_names_activity=new Intent(this,Players_Names.class);
        startActivity(player_names_activity);
    }
}
