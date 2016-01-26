package com.test.chris.makeaproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.chris.makeaproject.R;

/**
 * Created by chris on 2015/12/13.
 */
public class PersonCenterFragment extends Fragment {

    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_person_center,container,false);

        init();
        initData();
        return mView;
    }

    public void init() {

    }

    public void initData() {

    }
}
