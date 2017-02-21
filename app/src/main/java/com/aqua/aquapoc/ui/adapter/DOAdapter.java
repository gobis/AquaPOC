package com.aqua.aquapoc.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondValuesModel;

import java.util.List;

/**
 * Created by iningosu on 2/21/2017.
 */

public class DOAdapter extends BaseAdapter {


    Context mContext;
    List<pondValuesModel> mDOList;

    public DOAdapter(Context context){
        mContext = context ;
    }


    public void setData( List<pondValuesModel> siteModelList ){
        mDOList = siteModelList ;
    }

    @Override
    public int getCount() {
        int count =  0 ;

        if(mDOList != null){
            count = mDOList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return mDOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DOAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.table_record_row, null);
            holder = new DOAdapter.ViewHolder();
            holder.timestamp = (TextView)convertView.findViewById(R.id.timestamp);
            holder.doValue = (TextView)convertView.findViewById(R.id.param_value);
            convertView.setTag(holder);
        } else {
            holder = (DOAdapter.ViewHolder)convertView.getTag();
        }
        holder.timestamp.setText(((pondValuesModel)getItem(position)).getUpdatedTime());
        String do_value =  String.valueOf( ((pondValuesModel)getItem(position)).getDO() );
        holder.doValue.setText(do_value);

        return convertView;
    }

    public static class ViewHolder {
        public TextView timestamp;
        public TextView doValue;
    }


}
