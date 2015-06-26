package com.example.oao.finalproject;

/**
 * Created by OAO on 2015/6/21.
 */
public class HistoryView {

    private int _ID;
    private String Item;
    private int Price;

    public HistoryView(int _ID, String Item, int Price){
        this._ID = _ID;
        this.Item = Item;
        this.Price = Price;
    }

    public int get_ID(){
        return _ID;
    }

    public void set_ID(int _ID){
        this._ID = _ID;
    }

    public String getItem(){
        return Item;
    }

    public void setItem(String Item){
        this.Item = Item;
    }

    public int getPrice(){
        return Price;
    }

    public void setPrice(int Price){
        this.Price = Price;
    }
}
