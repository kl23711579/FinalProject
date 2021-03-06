package com.example.oao.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OAO on 2015/6/20.
 */


public class DBuse {
    /* Table Name */
    public static final String TABLE_NAME = "purchase";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id"; /* int */

    // Other Cloumn build
    public static final String DAY_COLUMN = "day"; /* int */
    public static final String MONTH_COLUMN = "month"; /* int */
    public static final String YEAR_COLUMN = "year"; /* int */
    public static final String ITEM_COLUMN = "item"; /* String */
    public static final String PRICE_COLUMN = "price"; /* int */
    public static final String OTHER_COLUMN = "other"; /* String */

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE purchase (_id INTEGER PRIMARY KEY AUTOINCREMENT, day INTEGER NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, " +
            "item TEXT NOT NULL, price INTEGER NOT NULL, other TEXT)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DBuse(Context context) {
        db = DB.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    //Insert to DB
    public void Insert(int year, int month, int day, String item, int value, String other){
        Log.v("Test", "2");
        String str = "INSERT INTO " + TABLE_NAME + "(year, month, day, item, price, other) " +
                        "VALUES (" + year  + ", " +
                                     month + ", " +
                                     day   + ", '" +
                                     item  + "', " +
                                     value + ", '" +
                                     other + "')";

        db.execSQL(str);
    }

    //Delete
    public void Delete(int _id){
        String str = "DELETE FROM " + TABLE_NAME + " WHERE _id=" + _id;
        db.execSQL(str);
    }

    //Get column id, item, price
    public List<HistoryView> get_Item_Price(int year, int month, int day){
        List<HistoryView> Item_Price = new ArrayList<>();
        /* SELECT _id, item, price FROM purchase WHERE year=year AND month=month AND day=day*/
        String str = "SELECT " + KEY_ID + "," + ITEM_COLUMN + "," + PRICE_COLUMN + " FROM " + TABLE_NAME +
                     " WHERE year=" + year + " AND month=" + month + " AND day=" + day;
        Cursor cursor = db.rawQuery(str, null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            HistoryView ip = new HistoryView(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            Item_Price.add(i, ip);
        }
        return Item_Price;
    }

    //Calc sum of price
    public int CalcSum(int year, int month){
        int sum = 0;
        /* SELECT price FROM purchase WHERE year=year AND month=month */
        String str = "SELECT " + PRICE_COLUMN + " FROM " + TABLE_NAME +
                     " WHERE year=" + year + " AND month=" + month;
        Cursor cursor = db.rawQuery(str, null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            sum+=cursor.getInt(0);
        }
        return sum;
    }
}
