package com.test.chris.makeaproject.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.chris.makeaproject.R;
import com.test.chris.makeaproject.entity.MainMenu;

import java.util.List;

/**
 * Created by chris on 2015/12/13.
 */
public class MainMenuAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<MainMenu> mainMenuList;
    private int iconSize;

    public MainMenuAdapter(Context context, List<MainMenu> mainMenuList) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        iconSize = mContext.getResources().getDimensionPixelSize(R.dimen.main_menu_icon_size);
        this.mainMenuList = mainMenuList;
    }

    @Override
    public int getCount() {
        return mainMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return mainMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getType(int position) {
        return mainMenuList.get(position).getType();
    }

    public void setMainMenuList(List<MainMenu> mainMenuList) {
        this.mainMenuList = mainMenuList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainMenu mainMenu = mainMenuList.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.app_menu_item, parent, false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.menu_text);
        View separator = convertView.findViewById(R.id.menu_separator);
        switch (mainMenu.getType()) {
            case MainMenu.TYPE_NO_ICON:
                separator.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                text.setText(mainMenu.getName());
                break;
            case MainMenu.TYPE_NORMAL:
                separator.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                text.setText(mainMenu.getName());
                Drawable icon = mContext.getResources().getDrawable(mainMenu.getIcon());
                setIconColor(icon);
                if (icon != null) {
                    icon.setBounds(0, 0, iconSize, iconSize);
                    TextViewCompat.setCompoundDrawablesRelative(text, icon, null, null, null);
                }
                break;

            case MainMenu.TYPE_SEPARATOR:
                text.setVisibility(View.GONE);
                separator.setVisibility(View.VISIBLE);
                break;
        }
        return convertView;
    }

    private void setIconColor(Drawable icon) {
        int textColorSecondary = android.R.attr.textColorSecondary;
        TypedValue value = new TypedValue();
        if (!mContext.getTheme().resolveAttribute(textColorSecondary, value, true)) {
            return;
        }
        int baseColor = mContext.getResources().getColor(value.resourceId);
        icon.setColorFilter(baseColor, PorterDuff.Mode.MULTIPLY);
    }

}
