package com.example.oao.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by OAO on 2015/6/18.
 */
public class MyCalendar extends Activity{

    private CalendarView MyCalendar;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.mycalendar);

        MyCalendar = (CalendarView)findViewById(R.id.calenderview1);

        //Get intent Data
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        //Use Data
        mYear = bundle.getInt("InputYear");
        mMonth = bundle.getInt("InputMonth") - 1;  //Because Month in array is turemonth+1 so in this should -1
        mDay = bundle.getInt("InputDay");

        //Set Calendar Date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);

        long milliTime = calendar.getTimeInMillis();
        MyCalendar.setDate(milliTime);

        MyCalendar.setOnDateChangeListener(DateChangeListener);
    }

    private CalendarView.OnDateChangeListener DateChangeListener= new CalendarView.OnDateChangeListener(){

        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
            //Intent
            Intent intent = new Intent();
            intent.setClass(MyCalendar.this, MainActivity.class);

            //Bundle
            Bundle MyDate = new Bundle();

            //Package data
            MyDate.putInt("InputYear", year);
            MyDate.putInt("InputMonth", month);
            MyDate.putInt("InputDay", dayOfMonth);

            //return data
            intent.putExtras(MyDate);
            setResult(RESULT_OK, intent);

            //finish activity
            finish();

        }
    };

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
