package com.example.android.music;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    public MyPagerAdapter(FragmentManager fm, int tabcount) {
        super(fm);
        tabCount = tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new fragment_party();
        } else if (position == 1){
            return new fragment_sad();
        } else
            return new fragment_romatic();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
