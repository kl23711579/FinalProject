package com.example.oao.finalproject;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private Button DateInputChoose;
    private EditText DateInput;
    private int InputDay, InputMonth, InputYear;
    static final int DATE_DIALOG_ID = 0;
    static final int CALENDAR_VIEW_ID = 1;
    private Button btnenter, btnclear;
    private DBuse dbuse;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DateInputChoose = (Button)findViewById(R.id.DateInputChoose);
        DateInput = (EditText)findViewById(R.id.DateInput);
        btnclear = (Button)findViewById(R.id.btnclear);
        btnenter = (Button)findViewById(R.id.btnenter);

        initialDate();
        dbuse = new DBuse(getApplicationContext());
        DateInputChoose.setOnClickListener(DateListener);
        btnclear.setOnClickListener(clearListener);
        btnenter.setOnClickListener(enterListener);
    }

    private void initialDate(){
        Log.v("Test","4");
        final Calendar c = Calendar.getInstance();
        InputDay = c.get(Calendar.DAY_OF_MONTH);
        InputMonth = c.get(Calendar.MONTH) + 1;
        InputYear = c.get(Calendar.YEAR);
        DateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);
    }

    private Button.OnClickListener DateListener = new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, MyCalendar.class);
            startActivityForResult(intent, CALENDAR_VIEW_ID);
        }
    };

    //Button Enter Action
    private Button.OnClickListener enterListener = new Button.OnClickListener(){
        public void onClick(View v){
            Log.v("Test","3");
            dbuse.Insert(2015,6,20,"water",20,"WTF");
        }
        //Test Data
        //2015,6,20,water,20,WTF
    };

    private Button.OnClickListener clearListener = new Button.OnClickListener(){
        public void onClick(View v){
            dbuse.Delete(1);
        }
    };

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
                    DateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
