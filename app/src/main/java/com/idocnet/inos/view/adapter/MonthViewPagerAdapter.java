package com.idocnet.inos.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.idocnet.inos.view.fragment.event.MonthDetailFragment;

import java.util.List;

public class MonthViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<MonthDetailFragment> fragments;
    public MonthViewPagerAdapter(FragmentManager fm, List<MonthDetailFragment> fragments) {
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

    public void setMonthOfYear(int dayOfYear){
        fragments.get(0).setMonthOfYear(dayOfYear-1);
        fragments.get(1).setMonthOfYear(dayOfYear);
        fragments.get(2).setMonthOfYear(dayOfYear+1);
    }
}
