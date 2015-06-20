package com.example.oao.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by OAO on 2015/6/20.
 */
public class DBuse {
    /* Table Name */
    private static final String TABLE_NAME = "purchase";

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
        String str = "INSERT INTO " + TABLE_NAME + "(year, month, day, item, price, other) " +
                        "VALUES (" + year  + ", " +
                                     month + ", " +
                                     day   + ", '" +
                                     item  + "', " +
                                     value + ", '" +
                                     other + "')";

        db.execSQL(str);
        //INSERT INTO purchase(year, month, day, item, price, other)
        //VALUES (1996, 02, 28, book, 666, WTF)
    }

    //Delete
    public void Delete(int _id){
        String str = "DELETE FROM " + TABLE_NAME + "WHERE _id=" + KEY_ID;
        db.execSQL(str);
    }


    /* insert = "INSERT INTO table01(year, month, day, product, price) " +
                                "values ("+n+", "+year+", "+month+", "+day+", '"+product+"', "+price+")";
                       */
}
