package com.test.chris.makeaproject.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.test.chris.makeaproject.bean.HttpExtraValues;
import com.test.chris.makeaproject.listener.OnHttpListeners;
import com.test.chris.makeaproject.tools.DIYUtiles;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chris on 2016/1/6.
 */
public class HttpServices {

    private static HttpServices instance;
    public static final int SUCCESS_HTTP = 1;
    public static final int FAILED_HTTP = 0;

    private HttpServices() {
    }

    public static HttpServices newInstance() {
        if (instance == null) {
            instance = new HttpServices();
        }
        return instance;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_HTTP:
                    HttpExtraValues sValues = (HttpExtraValues) msg.obj;
                    sValues.getOnHttpListeners().onSuccess(sValues);
                    break;
                case FAILED_HTTP:
                    HttpExtraValues fValues = (HttpExtraValues) msg.obj;
                    fValues.getOnHttpListeners().onFailure(fValues, fValues.isFailed());
                    break;
            }
        }
    };

    public void get(String url, OnHttpListeners onHttpListeners) {
        if (DIYUtiles.isHttpUrlFormRight(url) && onHttpListeners != null) {
            httpGet(url, onHttpListeners);
        }
    }

    private void httpGet(final String url, final OnHttpListeners onHttpListeners) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HttpExtraValues values = new HttpExtraValues();
                values.setStrData(result);
                values.setIsFailed(true);
                onHttpListeners.onSuccess(values);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                HttpExtraValues values = new HttpExtraValues();
                values.setStrData(ex.toString());
                values.setIsFailed(false);
                onHttpListeners.onFailure(values, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                HttpExtraValues values = new HttpExtraValues();
                values.setIsFailed(false);
                values.setStrData(cex.toString());
                onHttpListeners.onCancelled(values);
            }

            @Override
            public void onFinished() {
                onHttpListeners.onFinished();
            }
        });
    }

    /*options = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片尺寸
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//图片的ScaleType
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载过程中的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载错误的图片
                .build();*/
    public Boolean downLoadImg(String url, ImageView img,ImageOptions options) {
        if (DIYUtiles.isImgUrlValid(url) && img != null) {
            httpDownLoadImg(url, img,options);
            return true;
        }
        return false;
    }

    private void httpDownLoadImg(final String url, final ImageView img,final ImageOptions options) {
        x.image().bind(img,url,options);
    }

    public void post(String url, Bundle bundle, OnHttpListeners onHttpListeners) {
        if (DIYUtiles.isHttpUrlFormRight(url) && onHttpListeners != null) {
            httpPost(url, bundle, onHttpListeners);
        }
    }

    private void httpPost(final String url, final Bundle bundle, final OnHttpListeners onHttpListeners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
    }


    private void sendMessage(int what, HttpExtraValues values) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = values;
        handler.sendMessage(msg);
    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 100) {
                pics.add("http://" + src + ".jpg");
            }
        }
        return pics;
    }

}
