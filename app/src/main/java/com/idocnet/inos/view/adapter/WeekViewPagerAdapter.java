package com.idocnet.inos.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.idocnet.inos.view.fragment.event.WeekDetailFragment;

import java.util.List;

public class WeekViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<WeekDetailFragment> fragments;
    public WeekViewPagerAdapter(FragmentManager fm, List<WeekDetailFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void setWeekOfYear(int dayOfYear){
        fragments.get(0).setWeekOfYear(dayOfYear-1);
        fragments.get(1).setWeekOfYear(dayOfYear);
        fragments.get(2).setWeekOfYear(dayOfYear+1);
    }
}
