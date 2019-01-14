package com.idocnet.inos.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.idocnet.inos.view.fragment.event.EventCreateEventFragment;
import com.idocnet.inos.view.fragment.event.EventCreateTimeFragment;

public class EventCreatePagerAdapter extends FragmentPagerAdapter {
    public EventCreatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new EventCreateEventFragment();
            case 1:
                return new EventCreateTimeFragment();
            default:
                return new EventCreateEventFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Sự kiện";
            case 1:
                return "Thời gian";
            default:
                return "Sự kiện";
        }
    }
}
