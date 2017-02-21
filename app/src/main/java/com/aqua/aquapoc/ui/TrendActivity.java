package com.aqua.aquapoc.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondValuesModel;
import com.aqua.aquapoc.utility.utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iningosu on 1/3/2017.
 */

public class TrendActivity extends AppCompatActivity {


    ArrayList<pondValuesModel> pondValuesModelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pond_trend);


        getSupportActionBar().setTitle("Trends");

        // get data from the extras

        pondValuesModelList = getIntent().getParcelableArrayListExtra(utils.POND_TREND);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTableActivity();
            }
        });


        BarData data = new BarData(getXAxisValues(), getDataSet());



        chart.setData(data);
        chart.setDescription("Pond Trend");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }



    private void navigateToTableActivity(){
        Intent intent  = new Intent(TrendActivity.this,PondTableActivity.class);
        intent.putParcelableArrayListExtra(utils.POND_TREND,pondValuesModelList);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //  test data

    private List<IBarDataSet> getDataSet() {
        List<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        ArrayList<BarEntry> valueSet3 = new ArrayList<>();


         int pos = 0 ;

        for(pondValuesModel pvm : pondValuesModelList){
            valueSet1.add(new BarEntry(pvm.getpH(), pos));
            valueSet2.add(new BarEntry(pvm.getDO(), pos));
            valueSet3.add(new BarEntry(pvm.getTemp(), pos));
            pos++;
        }




       /* BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);



        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        */

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "pH");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "DO");
        barDataSet2.setColor(Color.rgb(193, 37, 82));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Temp");
        barDataSet3.setColor(Color.rgb(136, 180, 187));
      // ColorTemplate.COLORFUL_COLORS
      //  207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187)


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        return dataSets;
    }

    private List<String> getXAxisValues() {
        List<String> xAxis = new ArrayList<>();

        for(pondValuesModel pvm : pondValuesModelList){
            xAxis.add(pvm.getUpdatedTime());
        }

       /* xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");*/
        return xAxis;
    }




}
