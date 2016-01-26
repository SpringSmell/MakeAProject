package com.test.chris.makeaproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.adapter.DemoListAdapter;
import com.test.chris.makeaproject.frame.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 2016/1/5.
 */
public class DemoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView demoList;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_demo);
        mContext=this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        demoList= (ListView) findViewById(R.id.demoMainList);
    }

    @Override
    public void initData() {
        DemoListAdapter adapter=new DemoListAdapter(mContext,getListData());
        demoList.setAdapter(adapter);

        demoList.setOnItemClickListener(this);
    }

    @Override
    public void initTitle() {

    }

    private List getListData(){
        List dataList=new ArrayList();
        dataList.add("光照传感器");
        dataList.add("速度传感器");
        dataList.add("方向传感器");
        dataList.add("下载测试");
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                LightSensorActivity.startAction(mContext);
                break;
            case 1:
                VelocitySensorActivity.startAction(mContext);
                break;
            case 2:
                OrientationSensorActivity.startAction(mContext);
                break;
            case 3:
                HttpTestActivity.startAction(mContext);
                break;
        }
    }

    public static void startAction(Context context){
        Intent intent=new Intent(context,DemoActivity.class);
        context.startActivity(intent);
    }

}
