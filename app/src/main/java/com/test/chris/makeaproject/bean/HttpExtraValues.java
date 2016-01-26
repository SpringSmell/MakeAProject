package com.test.chris.makeaproject.bean;

import android.widget.ImageView;

import com.test.chris.makeaproject.listener.OnHttpListeners;

import java.io.InputStream;

/**
 * Created by chris on 2016/1/6.
 */
public class HttpExtraValues {

    private String strData;
    private OnHttpListeners onHttpListeners;
    private InputStream inputStream;
    private byte[] bytes;
    private ImageView imageView;
    private boolean isFailed;

    public OnHttpListeners getOnHttpListeners() {
        return onHttpListeners;
    }

    public void setOnHttpListeners(OnHttpListeners onHttpListeners) {
        this.onHttpListeners = onHttpListeners;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setIsFailed(boolean isFailed) {
        this.isFailed = isFailed;
    }
}
