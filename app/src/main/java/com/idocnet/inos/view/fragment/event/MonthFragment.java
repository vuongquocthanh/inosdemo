package com.idocnet.inos.view.fragment.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idocnet.inos.R;
import com.idocnet.inos.callback.MonthChangeNameListener;
import com.idocnet.inos.utils.Constants;
import com.idocnet.inos.view.adapter.MonthViewPagerAdapter;
import com.idocnet.inos.view.fragment.EventFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonthFragment extends Fragment{
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<MonthDetailFragment> fragments = new ArrayList<>();
    private int monthOfYear;
    private MonthChangeNameListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        ButterKnife.bind(this, view);

        listener = (EventFragment) getParentFragment();

        fragments.add(MonthDetailFragment.newInstance(-1));
        fragments.add(MonthDetailFragment.newInstance(0));
        fragments.add(MonthDetailFragment.newInstance(1));

        MonthViewPagerAdapter viewPagerAdapter = new MonthViewPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE) {
                    if (viewPager.getCurrentItem() < 1) {
                        previousSwipe();
                    } else if (viewPager.getCurrentItem() > 1) {
                        nextSwipe();
                    }
                    viewPagerAdapter.setMonthOfYear(monthOfYear);
                    viewPager.setCurrentItem(1, false);
                }

            }
        });

        viewPager.setCurrentItem(1, false);
        listener.changeMonthName(getMonthName());

        return view;
    }

    private void previousSwipe(){
        monthOfYear--;
        listener.changeMonthName(getMonthName());
    }

    private void nextSwipe(){
        monthOfYear++;
        listener.changeMonthName(getMonthName());
    }

    private String getMonthName(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthOfYear);
        return Constants.months[calendar.get(Calendar.MONTH)];
    }

}
