package com.test.chris.makeaproject.listener;

import com.test.chris.makeaproject.bean.HttpExtraValues;

import org.xutils.common.Callback;

/**
 * Created by chris on 2016/1/6.
 */
public abstract class OnHttpListeners {

    public abstract void onSuccess(HttpExtraValues values);

    public abstract void onFailure(HttpExtraValues values,Boolean isFailure);

    public void onCancelled(HttpExtraValues values){};

    public void onFinished() {

    }

}
