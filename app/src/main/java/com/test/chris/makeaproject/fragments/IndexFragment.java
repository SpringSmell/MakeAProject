package com.test.chris.makeaproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.activities.LightSensorActivity;
import com.test.chris.makeaproject.activities.OrientationSensorActivity;
import com.test.chris.makeaproject.activities.VelocitySensorActivity;

/**
 * Created by chris on 2015/12/12.
 */
public class IndexFragment extends Fragment implements View.OnClickListener {

    private View mViewLayout;
    private Context mContext;

    private TextView lightSenor;
    private TextView velocity;
    private TextView orientation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mViewLayout = LayoutInflater.from(mContext).inflate(R.layout.fragment_index, container, false);
        init();
        initData();
        return mViewLayout;
    }

    public void init() {
        lightSenor = (TextView) mViewLayout.findViewById(R.id.lightSenor);
        velocity = (TextView) mViewLayout.findViewById(R.id.velocitySenor);
        orientation= (TextView) mViewLayout.findViewById(R.id.orientationSenor);

        lightSenor.setOnClickListener(this);
        velocity.setOnClickListener(this);
        orientation.setOnClickListener(this);
    }

    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lightSenor:
                LightSensorActivity.startAction(mContext);
                break;
            case R.id.velocitySenor:
                VelocitySensorActivity.startAction(mContext);
                break;
            case R.id.orientationSenor:
                OrientationSensorActivity.startAction(mContext);
                break;
        }
    }
}
