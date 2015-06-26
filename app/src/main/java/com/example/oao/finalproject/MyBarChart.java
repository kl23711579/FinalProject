package com.example.oao.finalproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;

/**
 * Created by OAO on 2015/6/25.
 */
public class MyBarChart extends ActionBarActivity{

    protected BarChart MyBar;
    private Typeface mTf;
    private DBuse db;
    private int BarYear, BarMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart);

        MyBar = (BarChart)findViewById(R.id.BarChart1);

        //Get Data
        Bundle bundle = this.getIntent().getExtras();

        BarYear = bundle.getInt("Year");
        BarMonth = bundle.getInt("Month");

        //DB
        db = new DBuse(getApplicationContext());

        //MyBar.setOnChartValueSelectedListener(MyBarChart);
        MyBar.setDrawBarShadow(false);
        MyBar.setDrawValueAboveBar(true);

        // if more than 31 entries are displayed in the chart, no values will be drawn
        MyBar.setMaxVisibleValueCount(31);

        MyBar.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        MyBar.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = MyBar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);


        YAxis leftAxis = MyBar.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = MyBar.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(8);
        rightAxis.setSpaceTop(15f);

        Legend l = MyBar.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

        setData(BarMonth);

    }

    private void setData(int month){
        ArrayList<String> xValues = new ArrayList<>();
        ArrayList<BarEntry> yValues = new ArrayList<>(); //Data
        for(int i = 0; i < month; i++){
            xValues.add(String.valueOf(i+1));
            yValues.add(new BarEntry((float)db.CalcSum(BarYear, i+1), i));
        }

        BarDataSet set1 = new BarDataSet(yValues, "Month Cost");  //柱　+ NAME
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<>();   //一堆柱 ， 因只有一SET資料所有只有一個柱
        dataSets.add(set1);

        BarData data = new BarData(xValues, dataSets);
        // data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);

        MyBar.setData(data);

    }
}
