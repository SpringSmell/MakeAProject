package com.test.chris.makeaproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.bean.HttpExtraValues;
import com.test.chris.makeaproject.config.ConfigUrl;
import com.test.chris.makeaproject.frame.BaseActivity;
import com.test.chris.makeaproject.listener.OnHttpListeners;
import com.test.chris.makeaproject.services.HttpServices;

import org.w3c.dom.Text;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

/**
 * Created by chris on 2016/1/6.
 */
public class HttpTestActivity extends BaseActivity {

    private TextView showData;
    private ImageView showImg;
    private ImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_http_test);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        showData= (TextView) findViewById(R.id.showDataTest);
        showImg= (ImageView) findViewById(R.id.showImgTest);
        options = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片尺寸
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//图片的ScaleType
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载过程中的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载错误的图片
                .build();
    }

    @Override
    public void initData() {
        HttpServices httpServices=HttpServices.newInstance();
        httpServices.downLoadImg(ConfigUrl.TEST_IMG2, showImg,options);
    }

    @Override
    public void initTitle() {

    }

    public static void startAction(Context context){
        Intent intent=new Intent(context,HttpTestActivity.class);

        context.startActivity(intent);
    }
}
