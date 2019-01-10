package com.idocnet.inos.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.idocnet.inos.view.fragment.event.DayDetailFragment;
import com.idocnet.inos.view.fragment.event.MonthDetailFragment;

import java.util.List;

public class DayViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<DayDetailFragment> fragments;
    public DayViewPagerAdapter(FragmentManager fm, List<DayDetailFragment> fragments) {
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

    public void setDayOfYear(int dayOfYear){
        fragments.get(0).setDayOfYear(dayOfYear-1);
        fragments.get(1).setDayOfYear(dayOfYear);
        fragments.get(2).setDayOfYear(dayOfYear+1);
    }
}
