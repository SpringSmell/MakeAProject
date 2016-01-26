package com.test.chris.makeaproject.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.adapter.MainMenuAdapter;
import com.test.chris.makeaproject.bean.HttpExtraValues;
import com.test.chris.makeaproject.entity.FragmentTabHost;
import com.test.chris.makeaproject.entity.MainMenu;
import com.test.chris.makeaproject.fragments.IndexFragment;
import com.test.chris.makeaproject.fragments.PersonCenterFragment;
import com.test.chris.makeaproject.frame.MainApplication;
import com.test.chris.makeaproject.listener.OnHttpListeners;
import com.test.chris.makeaproject.services.HttpServices;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chris on 2015/12/11.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private TextView mainToolbarTitle;
    private ImageView mainToolbarRight;
    private DrawerLayout mDrawerLayout;

    private long exitTime = 0;

    private ListView menuListView;
    private MainMenuAdapter mMainMenuAdapter;
    private List<MainMenu> mainMenuList;
    private View headView;

    private android.support.v4.app.FragmentTabHost mFragmentTabHost;
    private List<FragmentTabHost> fragmentTabHostBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.newInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        init();
        initData();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        fragmentTabHostBeanList = new LinkedList<>();
        mainMenuList = new LinkedList<>();

        headView = LayoutInflater.from(this).inflate(R.layout.app_menu_head, null);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mainToolbarTitle = (TextView) findViewById(R.id.main_toolbar_title);
        mainToolbarRight = (ImageView) findViewById(R.id.main_toolbar_right);
        mFragmentTabHost = (android.support.v4.app.FragmentTabHost) findViewById(android.R.id.tabhost);

        menuListView = (ListView) findViewById(R.id.main_menu);
        mMainMenuAdapter = new MainMenuAdapter(this, mainMenuList);
        menuListView.setAdapter(mMainMenuAdapter);
        menuListView.addHeaderView(headView);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.main_menu_open, R.string.main_menu_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mainToolbarTitle.setOnClickListener(this);
        mainToolbarRight.setOnClickListener(this);
        menuListView.setOnItemClickListener(this);
    }

    private void initData() {

        fragmentTabHostBeanList.add(new FragmentTabHost(R.string.index, R.drawable.selector_tab_index, IndexFragment.class));
        fragmentTabHostBeanList.add(new FragmentTabHost(R.string.test_fragment1, R.drawable.selector_tab_index, IndexFragment.class));
        fragmentTabHostBeanList.add(new FragmentTabHost(R.string.test_fragment2, R.drawable.selector_tab_index, IndexFragment.class));
        fragmentTabHostBeanList.add(new FragmentTabHost(R.string.person_center, R.drawable.selector_tab_index, PersonCenterFragment.class));

        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.tab_host_content);
        for (FragmentTabHost tabHostBean : fragmentTabHostBeanList) {
            TabHost.TabSpec spec = mFragmentTabHost.newTabSpec(getString(tabHostBean.getTitle()));
            spec.setIndicator(buildIndicator(tabHostBean));
            mFragmentTabHost.addTab(spec, tabHostBean.getFragment(), null);
        }
        mFragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mFragmentTabHost.setCurrentTab(0);

        //侧滑菜单
        mainMenuList.add(new MainMenu("Demo", R.mipmap.ic_launcher));
        mainMenuList.add(new MainMenu("地图", R.mipmap.ic_launcher));
        mainMenuList.add(new MainMenu("test3", R.mipmap.ic_launcher));
        mainMenuList.add(new MainMenu());
        mainMenuList.add(new MainMenu("sub"));
        mainMenuList.add(new MainMenu("sub item1", R.mipmap.ic_launcher));
        mainMenuList.add(new MainMenu("sub item2", R.mipmap.ic_launcher));
        mainMenuList.add(new MainMenu("sub item3", R.mipmap.ic_launcher));
        mMainMenuAdapter.setMainMenuList(mainMenuList);
        mMainMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - exitTime > 2000) {
                exitTime = System.currentTimeMillis();
                showSnackbar(getWindow().getDecorView(), getString(R.string.once_again_press), getString(R.string.cancel), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitTime = 0;
                    }
                });
            } else {
                MainApplication.newInstance().exit();
                super.onBackPressed();
            }
        }
    }

    private View buildIndicator(FragmentTabHost tabHostBean) {
        View tab = LayoutInflater.from(this).inflate(R.layout.app_tab_item, null);
        TextView hint = (TextView) tab.findViewById(R.id.tab_hint);
        hint.setText(tabHostBean.getTitle());
        ImageView icon = (ImageView) tab.findViewById(R.id.tab_icon);
        icon.setImageResource(tabHostBean.getIcon());

        return tab;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_toolbar_right:
                showSnackbar(v, "hint information", "Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(R.string.hint_information);
                    }
                });
                break;
            case R.id.main_toolbar_title:
                showToast(R.string.hint_information);
                break;
        }
    }

    private void showToast(int content) {
        Toast.makeText(this, getString(content), Toast.LENGTH_SHORT).show();
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    private void showSnackbar(View view, String hint, String action, View.OnClickListener clickListener) {
        Snackbar.make(view, hint, Snackbar.LENGTH_SHORT).setAction(action, clickListener).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://head
                showToast(0 + "");
                break;
            case 1://Demo
                DemoActivity.startAction(this);
                break;
            case 2://地图
                HttpServices.newInstance().get("http://192.168.1.231:8080/YaSi_English/selectOneArticleById?str=1", new OnHttpListeners() {

                    @Override
                    public void onSuccess(HttpExtraValues values) {
                        showToast(values.getStrData());
                    }

                    @Override
                    public void onFailure(HttpExtraValues values, Boolean isFailure) {
                        showToast(values.getStrData());
                    }
                });
                break;
            case 5:
                showToast(5 + "");
                break;
            case 6:
                showToast(6 + "");
                break;
            case 7:
                showToast(7 + "");
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
