package com.example.oao.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OAO on 2015/6/20.
 */
public class DB extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String DBName = "mDB.db";
    private final static String _TableName = "mTable";
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        /* super 關鍵字指的是目前這個 class 的上一層，也就是 SQLiteOpenHelper */
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DB(context, DBName, null, VERSION).getWritableDatabase();
        }

        return database;
    }

    /* 如果 Android 載入時找不到生成的資料庫檔案，就會觸發 onCreate */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /* 如果資料庫結構有改變了就會觸發 onUpgrade */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
