package com.example.oao.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by OAO on 2015/6/22.
 */
public class History extends ActionBarActivity{

    private ListView HisList;
    List<HistoryView>history_List = new ArrayList<HistoryView>();
    private MyAdapter myAdapter;
    private int InputDay, InputMonth, InputYear;
    private TextView HistoryDateInput;
    private Button btnChooseDate;
    static final int CALENDAR_VIEW_ID = 1;
    private DBuse dbuse1;
    Cursor cursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        HisList = (ListView)findViewById(R.id.List);
        HistoryDateInput = (TextView)findViewById(R.id.HistoryDateInput);
        btnChooseDate = (Button)findViewById(R.id.HistoryDateInputChoose);

        initialDate();

        btnChooseDate.setOnClickListener(DateListener);
    }

    private void initialDate(){
        final Calendar c = Calendar.getInstance();
        InputDay = c.get(Calendar.DAY_OF_MONTH);
        InputMonth = c.get(Calendar.MONTH) + 1;
        InputYear = c.get(Calendar.YEAR);
        HistoryDateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);

        dbuse1 = new DBuse(this);
        history_List = dbuse1.get_Item_Price(InputYear, InputMonth, InputDay);
        myAdapter = new MyAdapter(this, history_List);
        HisList.setAdapter(myAdapter);
    }

    private Button.OnClickListener DateListener = new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(History.this, MyCalendar.class);

            //Bundle
            Bundle Date = new Bundle();

            //Package Date
            Date.putInt("InputYear", InputYear);
            Date.putInt("InputMonth", InputMonth);
            Date.putInt("InputDay", InputDay);

            //Send Data
            intent.putExtras(Date);
            startActivityForResult(intent, CALENDAR_VIEW_ID);

        }
    };


    public void myDialog(final int _ID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbuse1.Delete(_ID);

                        history_List = dbuse1.get_Item_Price(InputYear, InputMonth, InputDay);
                        myAdapter = new MyAdapter(History.this, history_List);
                        HisList.setAdapter(myAdapter);
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog ad = builder.create();
        ad.show();
    }

    //MyCalendar return data output
    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data){
        switch (requestcode){
            case CALENDAR_VIEW_ID:
                if(resultCode == RESULT_OK){
                    Bundle MyDate = data.getExtras();

                    InputYear = MyDate.getInt("InputYear");
                    InputMonth = MyDate.getInt("InputMonth") + 1;
                    InputDay = MyDate.getInt("InputDay");
                    HistoryDateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);

                    history_List = dbuse1.get_Item_Price(InputYear, InputMonth, InputDay);
                    myAdapter = new MyAdapter(this, history_List);
                    HisList.setAdapter(myAdapter);
                }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.Enter:
                intent.setClass(History.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Stat:
                intent.setClass(History.this,Stat.class);
                startActivity(intent);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
