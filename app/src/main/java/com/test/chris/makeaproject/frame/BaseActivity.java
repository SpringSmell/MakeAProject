package com.test.chris.makeaproject.frame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.test.chris.makeaproject.R;

/**
 * Created by chris on 2015/12/13.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();
        initTitle();
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        MainApplication.newInstance().addActivity(this);
    }

    public abstract void init();
    public abstract void initData();
    public abstract void initTitle();

    protected void backValid(){
        findViewById(R.id.titleLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainApplication.newInstance().removeActivity(this);
    }

    protected void showToast(CharSequence content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
