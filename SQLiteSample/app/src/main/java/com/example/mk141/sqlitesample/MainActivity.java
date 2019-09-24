package com.example.mk141.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    TextView showing_names;
    EditText input;
    RadioButton add;
    RadioButton delete;
    My_DataBase_Handler db_handler;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        showing_names=findViewById(R.id.showing_name);
        input=findViewById(R.id.input);
        add=findViewById(R.id.add_radioButton2);
        delete=findViewById(R.id.delete_radioButton);
        db_handler=new My_DataBase_Handler(this,null,null,1);
        print_all();
    }
    public void Add(View view)
    {
        button.setText("Add");
    }
    public void Delete(View view)
    {
        button.setText("Delete");
    }
    public void pressed(View view)
    {
            if(add.isChecked())
            {
                Products products=new Products(input.getText().toString());
                db_handler.add_name(products);
                print_all();
            }
            else if(delete.isChecked())
            {
                db_handler.delete_name(input.getText().toString());
                print_all();
            }
            input.setText("");
    }
    public void print_all()
    {
        showing_names.setText(db_handler.show_all());
    }
}
