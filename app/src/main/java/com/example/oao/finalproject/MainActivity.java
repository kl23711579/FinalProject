package com.example.oao.finalproject;

import android.app.Dialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DateInputChoose = (Button)findViewById(R.id.DateInputChoose);
        DateInput = (EditText)findViewById(R.id.DateInput);

        initialDate();
        DateInputChoose.setOnClickListener(DateListener);
    }

    private void initialDate(){
        Log.v("Test","1");
        final Calendar c = Calendar.getInstance();
        InputDay = c.get(Calendar.DAY_OF_MONTH);
        InputMonth = c.get(Calendar.MONTH) + 1;
        InputYear = c.get(Calendar.YEAR);
        DateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);
    }

    private Button.OnClickListener DateListener = new Button.OnClickListener(){
        public void onClick(View v){
            Dialog dialog = new Dialog(getBaseContext());
            Toast.makeText(getBaseContext(),"1" ,Toast.LENGTH_LONG).show();
            dialog.setContentView(R.layout.mycalendar);

            CalendarView cal = (CalendarView)dialog.findViewById(R.id.calenderview1);

            Log.v("Test","1");

            cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    InputDay = dayOfMonth;
                    InputMonth = month;
                    InputYear = year;
                    DateInput.setText(InputDay + "/" + InputMonth + "/" + InputYear);
                }
            });
            dialog.show();
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
