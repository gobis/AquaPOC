package com.aqua.aquapoc.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aqua.aquapoc.R;
import com.aqua.aquapoc.model.siteModel;

import java.util.List;

/**
 * Created by iningosu on 1/3/2017.
 */

public class siteListAdapter extends BaseAdapter {


    Context mContext;
    List<siteModel> mSiteModelList ;

    public siteListAdapter(Context context){
        mContext = context ;
    }


    public void setData( List<siteModel> siteModelList ){
        mSiteModelList = siteModelList ;
    }

    @Override
    public int getCount() {
        int count =  0 ;

        if(mSiteModelList != null){
            count = mSiteModelList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        return mSiteModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mSiteModelList.get(position).getSiteID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.site_list_row, null);
            holder = new ViewHolder();
            holder.sitename = (TextView)convertView.findViewById(R.id.site_name);
            holder.sitelocation = (TextView)convertView.findViewById(R.id.site_location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.sitename.setText(((siteModel)getItem(position)).getSiteName());
        holder.sitelocation.setText(((siteModel)getItem(position)).getSiteLocation());

        convertView.setBackgroundColor((((siteModel)getItem(position)).getAlert() == 0) ? Color.rgb(0, 255, 0):Color.rgb(255, 0, 0));

        return convertView;
    }

    public static class ViewHolder {
        public TextView sitename;
        public TextView sitelocation;
    }

}
