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
public class VelocitySensorActivity extends BaseActivity implements SensorEventListener {

    private TextView showData;
    private SensorManager sensorManager;
    private int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_velocity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        showData = (TextView) findViewById(R.id.velocityShowData);
    }

    @Override
    public void initData() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void initTitle() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, VelocitySensorActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 所以值应该大于9.8m/s2
        float xValue = Math.abs(event.values[0]);
        float yValue = Math.abs(event.values[1]);
        float zValue = Math.abs(event.values[2]);
        if (xValue > 15 || yValue > 15 || zValue > 15) {
            value++;
            showData.setText("xValue" + xValue + "yValue" + yValue + "zValue" + zValue + "摇一摇次数：" + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}
