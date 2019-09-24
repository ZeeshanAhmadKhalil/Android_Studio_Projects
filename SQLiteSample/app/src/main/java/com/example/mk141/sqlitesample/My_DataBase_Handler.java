package com.example.mk141.sqlitesample;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class My_DataBase_Handler extends SQLiteOpenHelper
{
    private static final int DB_version=1;
    private static final String DB_name="products.db";
    private static final String DB_table_name="name_table";
    private static final String DB_coulmn_id="_id";
    private static final String DB_column_name="_name";

    public My_DataBase_Handler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DB_name, factory, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String query="CREATE TABLE "+DB_table_name+"("+DB_coulmn_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DB_column_name+" TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_table_name);
        onCreate(sqLiteDatabase);
    }
    public void add_name(Products product)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB_column_name,product.get_name());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(DB_table_name,null,contentValues);
        db.close();
    }
    public void delete_name(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+DB_table_name+" WHERE "+DB_column_name+"=\""+name+"\";");
    }
    public String show_all()
    {
        String db_string="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT*FROM "+DB_table_name +" WHERE 1";
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(cursor.getColumnIndex("_name"))!=null) {
                db_string+= cursor.getString(cursor.getColumnIndex("_name"));
                db_string+="\n";
            }
            cursor.moveToNext();
        }
        db.close();
        return db_string;
    }
}