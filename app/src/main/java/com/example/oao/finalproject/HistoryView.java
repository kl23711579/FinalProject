package com.example.oao.finalproject;

import android.widget.Button;

/**
 * Created by OAO on 2015/6/21.
 */
public class HistoryView {
    private int type;
    /*
    type = 0  Date
    type = 1  Item
    type = 2  Price
     */

    private String Item;
    private int Price;
    private Button btnDel;

    public HistoryView(int type, String Item, int Price){
        this.type = type;
        this.Item = Item;
        this.Price = Price;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
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
