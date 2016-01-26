package com.test.chris.makeaproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.chris.makeaproject.R;

import java.util.List;

/**
 * Created by chris on 2016/1/5.
 */
public class DemoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> demoDataList;

    public DemoListAdapter(Context context,List<String> demoDataList){
        this.mContext=context;
        this.demoDataList=demoDataList;
    }

    @Override
    public int getCount() {
        return demoDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return demoDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView showData;
        if(null==convertView){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_demo,parent,false);
            showData= (TextView) convertView.findViewById(R.id.demoShowData);
        }else{
            showData= (TextView) convertView.getTag();
        }
        showData.setText(demoDataList.get(position));
        return convertView;
    }
}
