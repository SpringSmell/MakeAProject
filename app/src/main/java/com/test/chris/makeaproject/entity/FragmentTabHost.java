package com.test.chris.makeaproject.entity;

/**
 * Created by chris on 2015/12/12.
 */
public class FragmentTabHost {

    private int title;
    private int icon;
    private Class fragment;

    public FragmentTabHost(int title, int icon, Class fragment){
        if(title==0||icon==0||fragment==null){
            throw new NullPointerException("The title or icon or fragment ");
        }
        this.title=title;
        this.icon=icon;
        this.fragment=fragment;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
