package com.example.mk141.ticktacktoe;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
public class Players_Names extends AppCompatActivity
{
    EditText player1_name;
    EditText player2_name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players__names);
        player1_name=(EditText)findViewById(R.id.player1_name_choice);
        player2_name=(EditText)findViewById(R.id.player2_name_choice);
        player1_name.setText("Player1");
        player2_name.setText("Player2");
    }
    public void to_choice_activity(View view)
    {
        Intent player_choice_activity=new Intent(this,Players_Choice.class);
//        Intent game_result_activity=new Intent(this,Game_Results.class);
        String player1=player1_name.getText().toString();
        String player2=player2_name.getText().toString();
        player_choice_activity.putExtra("player1_name",player1);
        player_choice_activity.putExtra("player2_name",player2);
//        game_result_activity.putExtra("player1_name",player1);
//        game_result_activity.putExtra("player2_name",player2);
        startActivity(player_choice_activity);
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
}