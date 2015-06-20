package com.example.oao.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

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
