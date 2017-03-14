package com.aqua.aquapoc.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondValuesModel;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by iningosu on 2/21/2017.
 */

public class TemparatureAdapter extends BaseAdapter{


    Context mContext;
    List<pondValuesModel> mTempList ;

    public TemparatureAdapter(Context context){
        mContext = context ;
    }


    public void setData( List<pondValuesModel> siteModelList ){
        mTempList = siteModelList ;
    }

    @Override
    public int getCount() {
        int count =  0 ;

        if(mTempList != null){
            count = mTempList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return mTempList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TemparatureAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.table_record_row, null);
            holder = new TemparatureAdapter.ViewHolder();
            holder.timestamp = (TextView)convertView.findViewById(R.id.timestamp);
            holder.temparature = (TextView)convertView.findViewById(R.id.param_value);
            convertView.setTag(holder);
        } else {
            holder = (TemparatureAdapter.ViewHolder)convertView.getTag();
        }

        String ts = ((pondValuesModel)getItem(position)).getUpdatedTime().trim() ;

        if(null == ts || ts.length() == 0 ){
            ts = "--";
        }else{

            StringTokenizer tokenizer = new StringTokenizer(ts,"T");
            if(tokenizer.hasMoreTokens()) {
                tokenizer.nextToken();
                ts = tokenizer.nextToken();
            }
        }

        holder.timestamp.setText(ts);
        String temp =  String.valueOf( ((pondValuesModel)getItem(position)).getTemp() );
        holder.temparature.setText(temp);

        return convertView;
    }

    public static class ViewHolder {
        public TextView timestamp;
        public TextView temparature;
    }



}
