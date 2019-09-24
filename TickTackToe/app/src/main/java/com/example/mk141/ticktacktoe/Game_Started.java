package com.example.mk141.ticktacktoe;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Game_Started extends AppCompatActivity
{
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Boolean player1_cross_checked;
    String player1_symbol;
    Boolean player1_winner;
    String player1;
    String player2;
    int Counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__started);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        Bundle choices=getIntent().getExtras();
        if(choices==null)
            return;
        player1_cross_checked=choices.getBoolean("player1_cross_checked");
        player1=choices.getString("player1");
        player2=choices.getString("player2");
        if(player1_cross_checked)
            player1_symbol="X";
        else
            player1_symbol="O";
    }
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
    public void onClick1(View view)
    {
        btn1.setClickable(false);
        if(player1_cross_checked)
        {
            btn1.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn1.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick2(View view)
    {
        btn2.setClickable(false);
        if(player1_cross_checked)
        {
            btn2.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn2.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick3(View view)
    {
        btn3.setClickable(false);
        if(player1_cross_checked)
        {
            btn3.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn3.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick4(View view)
    {
        btn4.setClickable(false);
        if(player1_cross_checked)
        {
            btn4.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn4.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick5(View view)
    {
        btn5.setClickable(false);
        if(player1_cross_checked)
        {
            btn5.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn5.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick6(View view)
    {
        btn6.setClickable(false);
        if(player1_cross_checked)
        {
            btn6.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn6.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick7(View view)
    {
        btn7.setClickable(false);
        if(player1_cross_checked)
        {
            btn7.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn7.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick8(View view)
    {
        btn8.setClickable(false);
        if(player1_cross_checked)
        {
            btn8.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn8.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void onClick9(View view)
    {
        btn9.setClickable(false);
        if(player1_cross_checked)
        {
            btn9.setText("X");
            player1_cross_checked=false;
        }
        else
        {
            btn9.setText("O");
            player1_cross_checked=true;
        }
        Counter++;
        check_result();
    }
    public void check_result()
    {
        if (btn1.getText()==btn2.getText()&&btn2.getText()==btn3.getText()&&btn2.getText().toString()!="")
        {
            if(btn1.getText().toString()==player1_symbol)
            player1_winner=true;
            else
            player1_winner=false;
            Counter=10;
            Toast.makeText(getBaseContext(),"Here1",Toast.LENGTH_LONG).show();
        }
        else if (btn4.getText()==btn5.getText()&&btn5.getText()==btn6.getText()&&btn5.getText().toString()!="")
        {
            if(btn4.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
            Toast.makeText(getBaseContext(),"Here2",Toast.LENGTH_LONG).show();
        }
        else if (btn7.getText()==btn8.getText()&&btn8.getText()==btn9.getText()&&btn8.getText().toString()!="")
        {
            if(btn7.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
            Toast.makeText(getBaseContext(),"Here3",Toast.LENGTH_LONG).show();
        }
        else if (btn1.getText()==btn4.getText()&&btn4.getText()==btn7.getText()&&btn4.getText().toString()!="")
        {
            if(btn1.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
        }
        else if (btn2.getText()==btn5.getText()&&btn5.getText()==btn8.getText()&&btn5.getText().toString()!="")
        {
            if(btn2.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
        }
        else if (btn3.getText()==btn6.getText()&&btn6.getText()==btn9.getText()&&btn6.getText().toString()!="")
        {
            if(btn3.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
        }
        else if (btn1.getText()==btn5.getText()&&btn5.getText()==btn9.getText()&&btn5.getText().toString()!="")
        {
            if(btn1.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
        }
        else if (btn3.getText()==btn5.getText()&&btn5.getText()==btn7.getText()&&btn5.getText().toString()!="")
        {
            if(btn1.getText().toString()==player1_symbol)
                player1_winner=true;
            else
                player1_winner=false;
            Counter=10;
        }
        if(Counter>=9)
        {
            Intent game_result_activity=new Intent(this,Game_Results.class);
            game_result_activity.putExtra("Counter",Counter);
            game_result_activity.putExtra("player1_winner",player1_winner);
            game_result_activity.putExtra("player1",player1);
            game_result_activity.putExtra("player2",player2);
            startActivity(game_result_activity);
        }
    }
}