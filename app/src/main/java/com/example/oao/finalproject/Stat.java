package com.example.oao.finalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by OAO on 2015/6/22.
 */
public class Stat extends ActionBarActivity{

    private TextView StatYearMonth, TotalCost;
    private int InputMonth, InputYear;
    private DBuse dbuse;
    private Button Bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat);

        StatYearMonth = (TextView)findViewById(R.id.StatYearMonth);
        TotalCost = (TextView)findViewById(R.id.txtTotalCost);
        Bar = (Button)findViewById(R.id.btnBar);

        dbuse = new DBuse(getApplicationContext());
        initialDate();

        StatYearMonth.setOnClickListener(YearListener);
        Bar.setOnClickListener(BarListener);
    }

    private void initialDate(){
        final Calendar c = Calendar.getInstance();
        InputMonth = c.get(Calendar.MONTH) + 1;
        InputYear = c.get(Calendar.YEAR);
        String str = "Year：" + InputYear + "   Month：" + InputMonth;
        StatYearMonth.setText(str);
        TotalCost.setText(InputYear + "年" + InputMonth + "月總共花" + dbuse.CalcSum(InputYear, InputMonth) + "元");
    }

    private TextView.OnClickListener YearListener= new TextView.OnClickListener(){
        public void onClick(View v){
            chooseYM();
            TotalCost.setText(InputYear + "年" + InputMonth + "月總共花" + dbuse.CalcSum(InputYear, InputMonth) + "元");
        }
    };

    private Button.OnClickListener BarListener = new Button.OnClickListener(){
        public void onClick(View v){
            //Intent
            Intent intent = new Intent();
            intent.setClass(Stat.this, MyBarChart.class);

            //Bundle
            Bundle bundle = new Bundle();

            //Package Data
            bundle.putInt("Year", InputYear);
            bundle.putInt("Month", InputMonth);

            //Send Data
            Log.v("Test", "22");
            intent.putExtras(bundle);
            Log.v("Test", "25");
            startActivity(intent);
            Log.v("Test", "26");

        }
    };

    public void chooseYM(){
        //對話框物件，this是context物件，表示對話框所在activity
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.numberpicker);
        d.setTitle("Choose Year and Month");

        Button OK, Cancle;
        NumberPicker npYear, npMonth;

        //必須指定是哪個Activity、Dialog在findViewById，否則會出現NullPointerException，找不到對應物件。
        OK = (Button)d.findViewById(R.id.statok);
        Cancle = (Button)d.findViewById(R.id.statcancle);

        //Set NumberPicker
        npYear = createNP(d, R.id.npYear, 1970, 2030, InputYear);
        npMonth = createNP(d, R.id.npMonth, 1, 12, InputMonth);

        //Set Button
        final NumberPicker finalNpYear = npYear;
        final NumberPicker finalNpMonth = npMonth;
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputYear = finalNpYear.getValue();
                InputMonth = finalNpMonth.getValue();
                String str = "Year：" + InputYear + "   Month：" + InputMonth;
                StatYearMonth.setText(str);
                d.dismiss();
            }
        });
        Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        d.show();
    }

    public NumberPicker createNP(Dialog d, int id, int min, int Max, int num){
        NumberPicker np = (NumberPicker)d.findViewById(id);
        np.setMinValue(min);
        np.setMaxValue(Max);
        np.setValue(num);
        np.setFocusable(false);
        np.setFocusableInTouchMode(false);
        //Change format EX: 7 --> 07
        np.setFormatter(new NumberPicker.Formatter() {
            public String format(int value) {
                String tmpStr = String.valueOf(value);
                if (value < 10) {
                    tmpStr = "0" + tmpStr;
                }
                return tmpStr;
            }
        });
        return np;
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
                intent.setClass(Stat.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.History:
                intent.setClass(Stat.this,History.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
