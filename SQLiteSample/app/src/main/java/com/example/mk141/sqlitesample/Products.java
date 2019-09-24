package com.example.mk141.sqlitesample;
public class Products
{
    private int _id;
    private String _name;

    public  Products()
    {

    }

    public Products(String _name)
    {
        this._name = _name;
    }

    public int get_id()
    {
        return _id;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }
}
