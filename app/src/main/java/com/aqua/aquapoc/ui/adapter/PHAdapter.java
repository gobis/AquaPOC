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
import java.util.StringTokenizer;

/**
 * Created by iningosu on 2/21/2017.
 */

public class PHAdapter extends BaseAdapter {

    Context mContext;
    List<pondValuesModel> mPhList;

    public PHAdapter(Context context){
        mContext = context ;
    }


    public void setData( List<pondValuesModel> siteModelList ){
        mPhList = siteModelList ;
    }

    @Override
    public int getCount() {
        int count =  0 ;

        if(mPhList != null){
            count = mPhList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return mPhList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PHAdapter.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.table_record_row, null);
            holder = new PHAdapter.ViewHolder();
            holder.timestamp = (TextView)convertView.findViewById(R.id.timestamp);
            holder.phValue = (TextView)convertView.findViewById(R.id.param_value);
            convertView.setTag(holder);
        } else {
            holder = (PHAdapter.ViewHolder)convertView.getTag();
        }
        String ts = ((pondValuesModel)getItem(position)).getUpdatedTime().trim() ;

        if(null == ts ||  (null != ts  && ts.length() == 0 ) ){
            ts = "--";
        }else{

            StringTokenizer tokenizer = new StringTokenizer(ts,"T");
            tokenizer.nextToken();
            ts =  tokenizer.nextToken();
        }

        holder.timestamp.setText(ts);
        String do_value =  String.valueOf( ((pondValuesModel)getItem(position)).getpH() );
        holder.phValue.setText(do_value);

        return convertView;
    }

    public static class ViewHolder {
        public TextView timestamp;
        public TextView phValue;
    }

}
