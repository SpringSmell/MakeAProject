package com.test.chris.makeaproject.entity;

import android.text.TextUtils;

/**
 * Created by chris on 2015/12/13.
 */
public class MainMenu {

    private int icon;
    private String name;
    private int type;

    private static final int NO_ICON = 0;

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_NO_ICON = 1;
    public static final int TYPE_SEPARATOR = 2;

    public MainMenu() {
        this(null);
    }

    public MainMenu(String name) {
        this(name, NO_ICON);
    }

    public MainMenu(String name, int icon) {
        this.icon = icon;
        this.name = name;
        if (icon == NO_ICON && TextUtils.isEmpty(name)) {
            this.type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON) {
            this.type = TYPE_NO_ICON;
        } else {
            this.type = TYPE_NORMAL;
        }

        if (type != TYPE_SEPARATOR && TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("you need set a name for a non-SEPARATOR item");
        }
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public int getType(){
        return type;
    }
}
