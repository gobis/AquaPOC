package com.aqua.aquapoc.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.pondModel;

import java.util.List;

/**
 * Created by iningosu on 1/3/2017.
 */

public class pondListAdapter extends BaseAdapter{



    Context mContext;
    List<pondModel> mPondModelList ;

    public pondListAdapter(Context context){
        mContext = context ;
    }


    public void setData( List<pondModel> pondModelList ){
        mPondModelList = pondModelList ;
    }

    @Override
    public int getCount() {
        int count =  0 ;

        if(mPondModelList != null){
            count = mPondModelList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return mPondModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPondModelList.get(position).getPondID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.pond_list_row, null);
            holder = new ViewHolder();
            holder.pondname = (TextView)convertView.findViewById(R.id.pond_name);
            holder.pondlocation = (TextView)convertView.findViewById(R.id.pond_location);
            holder.pondtype = (TextView)convertView.findViewById(R.id.pond_type);
            holder.pondsize = (TextView)convertView.findViewById(R.id.pond_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.pondname.setText(((pondModel)getItem(position)).getPondName());
        holder.pondlocation.setText(((pondModel)getItem(position)).getPondLocation());
        holder.pondsize.setText(String.valueOf(((pondModel)getItem(position)).getPondSize()));
        holder.pondtype.setText(((pondModel)getItem(position)).getPondType());

        return convertView;
    }

    public static class ViewHolder {
        public TextView pondname;
        public TextView pondlocation;
        public TextView pondtype;
        public TextView pondsize;
    }


}
