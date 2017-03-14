package com.aqua.aquapoc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondValuesModel;
import com.aqua.aquapoc.ui.adapter.DOAdapter;
import com.aqua.aquapoc.ui.adapter.PHAdapter;
import com.aqua.aquapoc.ui.adapter.TemparatureAdapter;
import com.aqua.aquapoc.ui.adapter.siteListAdapter;
import com.aqua.aquapoc.utility.utils;

import java.util.ArrayList;

/**
 * Created by iningosu on 2/19/2017.
 */

public class PondTableActivity extends AppCompatActivity {


    ArrayList<pondValuesModel> pondValuesModelList;


    ListView mPHListView;
    ListView mDOListView;
    ListView mTempListView;


    TemparatureAdapter mTempAdapter ;
    DOAdapter mDoAdapter ;
    PHAdapter mPHAdapter ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);


        getSupportActionBar().setTitle("Trends");

        pondValuesModelList = getIntent().getParcelableArrayListExtra(utils.POND_TREND);


        mPHListView = (ListView)findViewById(R.id.listview_ph);
        mDOListView = (ListView) findViewById(R.id.listview_do);
        mTempListView = (ListView) findViewById(R.id.listview_temp);

        setDynamicHeight(mPHListView);
        setDynamicHeight(mDOListView);
        setDynamicHeight(mTempListView);


        mTempAdapter = new TemparatureAdapter(this);
        mTempListView.setAdapter(mTempAdapter);

        mPHAdapter = new PHAdapter(this);
        mPHListView.setAdapter(mPHAdapter);

        mDoAdapter = new DOAdapter(this);
        mDOListView.setAdapter(mDoAdapter);




        setDataSetForAll();


    }


    private void setDataSetForAll(){
        mTempAdapter.setData(pondValuesModelList);
        mTempAdapter.notifyDataSetChanged();

        mPHAdapter.setData(pondValuesModelList);
        mPHAdapter.notifyDataSetChanged();

        mDoAdapter.setData(pondValuesModelList);
        mDoAdapter.notifyDataSetChanged();
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



    private void setDynamicHeight(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }




}
