package com.test.chris.makeaproject.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.frame.BaseActivity;

/**
 * Created by chris on 2016/1/4.
 */
public class OrientationSensorActivity extends BaseActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private ImageView compassBackground;
    private ImageView compassPointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_orientation);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        compassBackground = (ImageView) findViewById(R.id.compassBackground);
        compassPointer = (ImageView) findViewById(R.id.compassPointer);
    }

    @Override
    public void initData() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void initTitle() {

    }

    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];
    private float lastRotateDegree;

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                accelerometerValues = event.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                magneticValues = event.values.clone();
                break;
        }
        float[] R = new float[9];
        float[] values = new float[3];
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
        SensorManager.getOrientation(R, values);
        //将计算出的旋转角度取反，用于旋转指南针背景图片
        float rotateDegree = -(float) Math.toDegrees(values[0]);
        if (Math.abs(rotateDegree - lastRotateDegree) > 1) {
            RotateAnimation rotateAnimation = new RotateAnimation(lastRotateDegree, rotateDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            compassBackground.startAnimation(rotateAnimation);
            lastRotateDegree=rotateDegree;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, OrientationSensorActivity.class);

        context.startActivity(intent);
    }
}
