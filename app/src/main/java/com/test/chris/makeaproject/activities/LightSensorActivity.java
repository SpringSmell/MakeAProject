package com.test.chris.makeaproject.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.frame.BaseActivity;

/**
 * Created by chris on 2016/1/4.
 */
public class LightSensorActivity extends BaseActivity implements SensorEventListener {


    private TextView showData;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_light_senor);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        showData= (TextView) findViewById(R.id.showData);
    }

    @Override
    public void initData() {
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values=event.values;
        showData.setText("Current light level is"+values[0]+"lx");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static void startAction(Context context){
        Intent intent=new Intent(context,LightSensorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorManager!=null) {
            sensorManager.unregisterListener(this);
        }
    }
}
